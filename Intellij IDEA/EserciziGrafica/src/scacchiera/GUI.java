package scacchiera;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class GUI extends Application {

    public static final Color[] colors = new Color[]{ Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

    public static final int N_COLONNE = 4, N_RIGHE = 5;

    private ArrayList<Casella> caselle;

    private GridPane tabella;

    private Button btnMescola, btnStampa, btnReset;

    private TextArea txtAreaLog;

    private void initGUI(){

        this.caselle = new ArrayList<>();
        this.initCaselle();
        this.tabella = new GridPane();
        tabella.setVgap(10);
        tabella.setHgap(10);
        this.popolaTabella();
        this.btnMescola = new Button("Shuffle");
        this.btnMescola.setOnAction((event) ->{

            Collections.shuffle(this.caselle);
            this.popolaTabella();

        });

        this.btnReset = new Button("RESET");
        this.btnReset.setOnAction((event) ->{

            this.initCaselle();
            this.popolaTabella();

        });

        this.btnStampa = new Button("Print");
        this.btnStampa.setOnAction((event) -> {
            for (int i = 0; i < N_RIGHE * N_COLONNE; i++) {

                if (i % N_COLONNE == 0 && i != 0)
                    this.txtAreaLog.setText(this.txtAreaLog.getText() + "\n");
                this.txtAreaLog.setText(this.txtAreaLog.getText() + this.caselle.get(i).toString() + " ");


            }
            this.txtAreaLog.setText(this.txtAreaLog.getText() + "\n\n");

        });

        this.txtAreaLog = new TextArea();
        this.txtAreaLog.setPrefSize(600,600);
        this.txtAreaLog.setEditable(false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI();
        HBox controls = new HBox(this.btnReset, this.btnMescola, this.btnStampa);

        primaryStage.setScene(new Scene(new VBox(controls, tabella)));
        primaryStage.sizeToScene();
        primaryStage.show();

        Stage printStage = new Stage();
        printStage.setScene(new Scene(new StackPane(this.txtAreaLog)));
        printStage.sizeToScene();
        printStage.setX(40);
        printStage.setY(40);
        printStage.show();



    }

    public void initCaselle(){

        this.caselle.clear();
        for( int i = 0; i < N_COLONNE * N_RIGHE; i++ ){

            Color background;
            Casella casella;
            if( i % 2 == 0 ){
                casella = new Casella(colors[0]);
                casella.setOnAction(CasellaHandler.getHandlerTipo1(casella));
            } else{

                casella = new Casella(colors[2]);
                casella.setOnAction(CasellaHandler.getHandlerTipo2(casella));

            }
            this.caselle.add(casella);
            casella.setPrefSize(80,80);

        }


    }

    private void popolaTabella(){
        this.tabella.getChildren().clear();
        for( int i  = 0; i < this.caselle.size(); i++ ){

            tabella.add(this.caselle.get(i), i%N_COLONNE, i/N_COLONNE);

        }

    }

    public static void main( String[] args ){

        Application.launch(args);

    }
}
