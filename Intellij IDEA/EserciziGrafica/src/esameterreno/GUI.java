package esameterreno;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    public static final int NUMERO_COLONNE = 8;
    public static final int NUMERO_RIGHE = 8;
    public static final int NUMERO_AUTO = 3;

    private GridPane griglia;
    private boolean piazzaAuto;

    private Button aggiungiAuto;
    private int numeroAutoDisponibili;
    private Text labelAutoDisponibili;

    private ArrayList<Automobile> automobili;

    private Button muoviSu;
    private Button muoviGiu;
    private Button muoviSinistra;
    private Button muoviDestra;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.piazzaAuto = false;
        this.numeroAutoDisponibili = NUMERO_AUTO;
        this.automobili = new ArrayList<>();

        this.labelAutoDisponibili = new Text("Auto rimanenti: " + this.numeroAutoDisponibili);
        this.labelAutoDisponibili.textProperty().addListener(((observable, oldValue, newValue) -> {

            if( numeroAutoDisponibili == 0 )
                aggiungiAuto.setDisable(true);

        }) );
        this.griglia = new GridPane();

        this.aggiungiAuto = new Button("Aggiungi auto");
        this.aggiungiAuto.setOnAction((event) -> {

            piazzaAuto = true;

        });

        this.griglia.setHgap(5);
        this.griglia.setVgap(5);
        for( int i = 0; i < NUMERO_RIGHE; i++ ){

            for( int j = 0; j < NUMERO_COLONNE; j++ ){

                Terreno cella;
                if( i == 0 || j == 0 || j == NUMERO_COLONNE -1 || i == NUMERO_RIGHE -1 )
                    cella = new Strada();
                else
                    cella = new Prato();

                final int I = i, J = j;
                cella.setOnAction((event) ->{

                    System.out.println("Ciao");
                    if( cella instanceof TerrenoOccupabile ) {
                        if (piazzaAuto) {
                            Automobile a = new Automobile(J,I);
                            System.out.println("Creo a " + I + " " + J);
                            if(((TerrenoOccupabile) cella).aggiungiEntita(a)){
                                piazzaAuto = false;
                                numeroAutoDisponibili--;
                                updateLabelAutoRimaste();
                                automobili.add(a);
                            }

                        }
                        else{
                            cambiaCella(J,I);
                        }
                    } else{

                        if( !piazzaAuto )
                            cambiaCella(J,I);
                        piazzaAuto = false;

                    }


                });

                this.griglia.add(cella, i,j);

            }

        }

        this.muoviSu = new Button("Su");
        this.muoviSu.setOnAction((event) ->{

            for( Automobile i : automobili ){

                System.out.println("X: " + i.getX() + " Y: "+ i.getY());
                if( i.getX() != 0) {
                    muoviAutomobile(i, i.getX()-1, i.getY());
                }

            }

        });

        this.muoviGiu = new Button("Giu");
        this.muoviGiu.setOnAction((event) ->{

            for( Automobile i : automobili ){

                System.out.println("X: " + i.getX() + " Y: "+ i.getY());
                if( i.getX() != NUMERO_RIGHE - 1) {
                    muoviAutomobile(i, i.getX()+1, i.getY());
                }

            }

        });

        this.muoviSinistra = new Button("Sinistra");
        this.muoviSinistra.setOnAction((event) -> {

            for( Automobile i : automobili ){

                System.out.println("X: " + i.getX() + " Y: "+ i.getY());
                if( i.getY() != 0) {
                    muoviAutomobile(i, i.getX(), i.getY()-1);
                }

            }

        });

        this.muoviDestra = new Button("Destra");
        this.muoviDestra.setOnAction((event) ->{

            for( Automobile i : automobili ){

                System.out.println("X: " + i.getX() + " Y: "+ i.getY());
                if( i.getY() != NUMERO_COLONNE-1) {
                    muoviAutomobile(i, i.getX(), i.getY()+1);
                }

            }

        });

        primaryStage.setScene(new Scene(new VBox(labelAutoDisponibili, aggiungiAuto, this.griglia, new HBox(this.muoviSu, this.muoviGiu, this.muoviSinistra, this.muoviDestra))));
        primaryStage.sizeToScene();
        primaryStage.show();



    }

    private void updateLabelAutoRimaste(){

        this.labelAutoDisponibili.setText("Auto rimaste: " + this.numeroAutoDisponibili);

    }

    private void cambiaCella(final int i, final int j){

        Terreno cella = (Terreno) this.getNodeByRowColumnIndex(i,j);
        this.griglia.getChildren().remove(cella);
        if( cella instanceof TerrenoOccupabile )
            cella = new Prato();
        else
            cella = new Strada();
        Terreno finalCella = cella;
        cella.setOnAction((event) -> {
            if( finalCella instanceof TerrenoOccupabile ) {
                if (piazzaAuto) {
                    Automobile a = new Automobile(i,j);
                    if(((TerrenoOccupabile) finalCella).aggiungiEntita(a)){
                        piazzaAuto = false;
                        numeroAutoDisponibili--;
                        updateLabelAutoRimaste();
                        automobili.add(a);
                    }

                }
                else{
                    cambiaCella(i,j);
                }
            } else{

                if( !piazzaAuto )
                    cambiaCella(i,j);
                piazzaAuto = false;

            }
        });

        this.griglia.add(cella, j,i);

    }

    public Node getNodeByRowColumnIndex (final int row, final int column ) {
        Node result = null;
        ObservableList<Node> childrens = this.griglia.getChildren();

        for (Node node : childrens) {
            if(this.griglia.getRowIndex(node) == row && this.griglia.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    public void muoviAutomobile( Automobile automobile, int newX, int newY ){

        Terreno cellaSuccessiva = (Terreno) getNodeByRowColumnIndex(newX, newY);
        if( cellaSuccessiva instanceof TerrenoOccupabile && ((TerrenoOccupabile) cellaSuccessiva).isAmmessa(Automobile.class.toString())){

            TerrenoOccupabile cellaCorrente = (TerrenoOccupabile) getNodeByRowColumnIndex(automobile.getX(), automobile.getY());
            boolean result = ((TerrenoOccupabile) cellaSuccessiva).aggiungiEntita(automobile);
            if(result) {
                cellaCorrente.rimuoviEntita(automobile);
                automobile.setX(newX);
                automobile.setY(newY);
            }
            System.out.println("Aggiunta? "+result);

        }
    }



    public static void main(String[] args){

        launch(args);

    }
}
