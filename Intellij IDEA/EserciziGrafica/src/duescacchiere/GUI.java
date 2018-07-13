package duescacchiere;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    public static final int N_RIGHE = 10, N_COLONNE = 10;
    public static final Paint COLORE_DEFAULT = Color.WHITE, COLORE_CLICK = Color.BLACK;

    private ArrayList<Cella> scacchieraSx, scacchieraDx;

    private GridPane gridPaneSx, gridPaneDx;
    private Button btnInverti, btnReset, btnStampa;
    private Text lblStato;

    private boolean uguale;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI();
        BorderPane root = new BorderPane();
        HBox scacchiere = new HBox(this.gridPaneSx, this.gridPaneDx);
        HBox.setHgrow(this.gridPaneSx, Priority.ALWAYS);
        HBox.setHgrow(this.gridPaneDx, Priority.ALWAYS);
        scacchiere.setSpacing(20);
        root.setCenter(scacchiere);
        root.setMinSize(400,400);

        HBox controlliInferiori = new HBox(this.btnReset, this.btnStampa);
        root.setBottom(controlliInferiori);

        HBox controlliSuperiori = new HBox(this.btnInverti, this.lblStato);
        root.setTop(controlliSuperiori);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args){

        Application.launch(args);

    }

    private void initGUI(){

        this.btnInverti = new Button("Inverti");
        this.btnReset = new Button("Reset");
        this.btnStampa = new Button("Stampa");
        this.uguale = true;
        this.lblStato = new Text("Uguale");

        this.scacchieraSx = new ArrayList<>();
        this.scacchieraDx = new ArrayList<>();
        this.gridPaneSx = new GridPane();
        this.gridPaneDx = new GridPane();

        this.initScacchieraSx();
        this.initScacchieraDx();
        this.popolaGridPane(this.gridPaneSx, this.scacchieraSx);
        this.popolaGridPane(this.gridPaneDx, this.scacchieraDx);


        this.btnReset.setOnAction((event) ->{

            this.initScacchieraSx();
            this.initScacchieraDx();
            this.popolaGridPane(this.gridPaneDx, this.scacchieraDx);
            this.popolaGridPane(this.gridPaneSx, this.scacchieraSx);

        });

        this.btnStampa.setOnAction((event) ->{

            for( Cella i : this.scacchieraSx )
                if( i.isAnnerita() )
                    System.out.println("Casella[ Riga:" +i.getRow()+" Colonna: " + i.getColumn() +" ]");

        });

        this.btnInverti.setOnAction((event) ->{

            uguale = !uguale;
            this.updateLabel();

        });




    }

    private void initScacchieraDx(){

        this.scacchieraDx.clear();
        for( int i = 0; i < N_RIGHE * N_COLONNE; i++ ){


            this.scacchieraDx.add(this.generaBottone(i/N_COLONNE, i%N_COLONNE, true));

        }

    }

    private void initScacchieraSx(){

        this.scacchieraSx.clear();
        for( int i = 0; i < N_RIGHE * N_COLONNE; i++ ){

            this.scacchieraSx.add(this.generaBottone(i/N_COLONNE, i%N_COLONNE, false));

        }

    }

    private Cella generaBottone(int r, int c, boolean destro){

        Cella btn = new Cella(r, c, COLORE_DEFAULT);
        btn.setMinSize(39,39);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null, new BorderWidths(1))));
        btn.setOnAction((event) ->{

           if( destro ){

               btn.setBackground(COLORE_DEFAULT);
               int indiceSx;
               if( this.uguale )
                   indiceSx = r*N_COLONNE+c;
               else
                   indiceSx = (N_RIGHE - r -1) * N_COLONNE +(N_COLONNE - c -1);
               this.scacchieraSx.get(indiceSx).setBackground(COLORE_DEFAULT);
           } else{

               int indiceDx;
               if( this.uguale )
                   indiceDx = r*N_COLONNE+c;
               else
                   indiceDx = (N_RIGHE - r -1) * N_COLONNE +(N_COLONNE - c -1);
               btn.setBackground(COLORE_CLICK);
               this.scacchieraDx.get(indiceDx).setBackground(COLORE_CLICK);

           }

        });
        GridPane.setVgrow(btn, Priority.ALWAYS);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        return btn;

    }

    private void popolaGridPane( GridPane gridPane, ArrayList<Cella> elementi ){

        gridPane.getChildren().clear();
        for( Cella elemento : elementi )
            gridPane.add(elemento, elemento.getColumn(), elemento.getRow());


    }

    private void updateLabel(){

        if( this.uguale )
            this.lblStato.setText("Uguale");
        else
            this.lblStato.setText("Invertito");

    }

}

