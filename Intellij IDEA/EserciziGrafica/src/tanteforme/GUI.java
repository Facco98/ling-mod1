package tanteforme;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    public static final int N_TIPO1 = 5, N_TIPO2 = 4;
    public static final int N_RIGHE = 3, N_COLONNE = 3;
    public static final int WIDTH = 400, HEIGHT = 400;
    private int cap = 319;

    private GridPane tabella;
    private ArrayList<Casella> caselle;
    private Button btnStampa, btnControlla, btnReset;

    private void initGUI(){

        this.caselle = new ArrayList<>();
        this.generaCaselle();
        this.tabella = new GridPane();
        this.tabella.setPrefSize(WIDTH, HEIGHT);
        this.tabella.setHgap(20);
        this.tabella.setAlignment(Pos.CENTER);
        this.popolaTabella();

        this.btnStampa = new Button("Stampa");
        this.btnControlla = new Button("Controlla");
        this.btnReset = new Button("Reset");

        this.btnStampa.setOnAction((event) ->{

            this.caselle.forEach((casella) -> System.out.println(casella));

        });

        this.btnReset.setOnAction((event) ->{

            this.generaCaselle();
            this.popolaTabella();

        });

        this.btnControlla.setOnAction((event) ->{

            boolean ugualeRighe = false, ugualeColonne = false;
            this.caselle.forEach((casella) ->{

                casella.resetBorder();

            });
            for( int i = 0; i < N_RIGHE; i ++ ) {
                boolean uguale = true;
                for (int j = 0; j < N_COLONNE-1 && uguale == true; j++){
                    if( this.caselle.get(i*N_COLONNE+j).getValue() == Casella.VUOTA || !this.caselle.get(i*N_COLONNE+j).getValue().equals(this.caselle.get(i*N_COLONNE+j+1).getValue()))
                        uguale = false;
                }
                if( uguale ){

                    for( int j = 0; j < N_COLONNE; j++ )
                        this.caselle.get(i*N_COLONNE+j).colorBorder();
                    System.out.println("HAI vinto");
                    ugualeRighe = true;

                }
            }

            for( int i = 0; i < N_COLONNE; i ++ ) {
                boolean uguale = true;
                for (int j = 0; j < N_RIGHE-1 && uguale == true; j++){
                    if( this.caselle.get(i+j * N_RIGHE).getValue() == Casella.VUOTA || !this.caselle.get(i+j * N_COLONNE).getValue().equals(this.caselle.get(i+(j+1)* N_COLONNE).getValue()))
                        uguale = false;
                }
                if( uguale ){

                    for( int j = 0; j < N_RIGHE; j++ )
                        this.caselle.get(i+j * N_COLONNE).colorBorder();
                    System.out.println("HAI vinto");
                    ugualeColonne = true;

                }
            }

            Text text;
            if( ugualeRighe || ugualeColonne ){
                text = new Text("HAI VINTO");
            } else
                text = new Text("Hai Perso");
            Stage stage = new Stage();
            stage.setScene(new Scene(new StackPane(text)));
            stage.show();

        });


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.initGUI();
        HBox controlli = new HBox(this.btnReset, this.btnStampa, this.btnControlla);
        VBox root = new VBox(controlli, this.tabella);
        VBox.setVgrow(this.tabella, Priority.ALWAYS);
        root.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setOnCloseRequest((evt) ->{

            System.exit(0);

        });

    }

    public static void main(String[] args){

        Application.launch(args);

    }

    private void generaCaselle(){

        int n_uno = N_TIPO1, n_due = N_TIPO2;
        this.caselle.clear();
        for( int i = 0; i < N_TIPO1 + N_TIPO2; i++ ){

            double random = Math.random();
            Casella casella;
            if( (random <= 0.5 && n_uno > 0) || (n_uno >0 && n_due == 0) ){

                casella = GeneratoreCasella.UNO.getCasella();
                n_uno --;

            } else{

                casella = GeneratoreCasella.DUE.getCasella();
                n_due --;

            }
            GridPane.setHgrow(casella, Priority.ALWAYS);
            GridPane.setVgrow(casella, Priority.ALWAYS);
            this.caselle.add(casella);
        }


    }

    private void popolaTabella(){

        this.tabella.getChildren().clear();
        for( int i = 0; i < N_RIGHE; i++ ){

            for( int j = 0; j < N_COLONNE; j++ ){

                this.tabella.add(this.caselle.get(i*N_COLONNE+j), j, i);


            }

        }

    }

}
