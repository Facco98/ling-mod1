package campionato;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    public static final int N_SQUADRE_GIRONE = 4;

    private Button btnPrimaGiornata, btnSecondaGiornata, btnTerzaGiornata, btnQuarti, btnSemifinali, btnFinale;
    private GridPane tabella;
    private ArrayList<Squadra> squadreIscritte;
    private ArrayList<Girone> gironi;

    public void initGUI(){

        this.btnPrimaGiornata = new Button("Prima giornata");
        this.btnSecondaGiornata = new Button("Seconda giornata");
        this.btnTerzaGiornata = new Button("Terza giornata");

        this.btnQuarti = new Button("Quarti");
        this.btnSemifinali = new Button("Semifinali");
        this.btnFinale = new Button("Finale");

        this.tabella = new GridPane();
        this.tabella.setHgap(30);
        this.tabella.setVgap(30);
        try {
            this.initSquadre();
            this.initGironi();
            this.popolaGridPane();

            this.btnPrimaGiornata.setOnAction((event) ->{

                this.showAnimationStage((evt) ->{
                    this.gironi.forEach((girone) ->{
                        girone.disputaGiornataSuccessiva();
                    });
                    this.popolaGridPane();
                    this.btnPrimaGiornata.setDisable(true);
                    this.btnSecondaGiornata.setDisable(false);
                });

            });

            this.btnSecondaGiornata.setDisable(true);
            this.btnSecondaGiornata.setOnAction((event) -> {

                this.showAnimationStage((evt) ->{
                    this.gironi.forEach((girone) -> girone.disputaGiornataSuccessiva());
                    this.popolaGridPane();
                    this.btnSecondaGiornata.setDisable(true);
                    this.btnTerzaGiornata.setDisable(false);
                });

            });

            this.btnTerzaGiornata.setDisable(true);
            this.btnTerzaGiornata.setOnAction((event) -> {

                this.showAnimationStage((evt) ->{
                    this.gironi.forEach((girone) -> girone.disputaGiornataSuccessiva());
                    this.popolaGridPane();
                    this.btnTerzaGiornata.setDisable(true);
                    this.btnQuarti.setDisable(false);
                });


            });

            this.btnQuarti.setDisable(true);
            this.btnQuarti.setOnAction((event) ->{

                this.showAnimationStage((evt) ->{
                    ArrayList<Partita> quarti = Girone.getQuarti(this.gironi);
                    System.out.println("===== QUARTI ===== ");
                    for( Partita partita : quarti ) {
                        partita.disputaNoPareggio();

                        System.out.println(partita.getSquadra1().getNome() + " vs " + partita.getSquadra2().getNome() + ": vince " + partita.getVincitore()[0].getNome());
                    }
                    this.btnQuarti.setDisable(true);
                    this.btnSemifinali.setDisable(false);
                });



            });

            this.btnSemifinali.setDisable(true);
            this.btnSemifinali.setOnAction((event) ->{

                this.showAnimationStage((evt) ->{
                    ArrayList<Partita> semifinali = Girone.getSemifinali();
                    System.out.println(" ===== SEMIFINALI ==== ");
                    for( Partita partita : semifinali ) {
                        partita.disputaNoPareggio();

                        System.out.println(partita.getSquadra1().getNome() + " vs " + partita.getSquadra2().getNome() + ": vince " + partita.getVincitore()[0].getNome());
                    }
                    this.btnSemifinali.setDisable(true);
                    this.btnFinale.setDisable(false);
                });


            });

            this.btnFinale.setDisable(true);
            this.btnFinale.setOnAction((event) ->{

                this.showAnimationStage((evt) ->{
                    Partita finale = Girone.generaFinale();
                    finale.disputaNoPareggio();
                    Stage finalStage = new Stage();
                    Scene finalScene;
                    Text winner = new Text(finale.getVincitore()[0].getNome());
                    winner.setTextAlignment(TextAlignment.CENTER);
                    winner.setFont(Font.font(35));
                    finalScene = new Scene(new StackPane(winner));
                    finalStage.setScene(finalScene);
                    finalStage.setOnCloseRequest((ev) -> System.exit(0));
                    finalStage.show();
                });


            });

        } catch( Exception ex ){

            ex.printStackTrace();
            System.exit(1);

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI();

        Stage stageControlli = new Stage();
        Scene sceneControlli = new Scene(new VBox(this.btnPrimaGiornata, this.btnSecondaGiornata, this.btnTerzaGiornata, this.btnQuarti, this.btnSemifinali, this.btnFinale));
        stageControlli.setScene(sceneControlli);
        stageControlli.sizeToScene();
        stageControlli.show();

        Scene scene = new Scene(tabella);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    public static void main(String[] args){

        Application.launch(args);

    }

    private void initSquadre() throws WrongNumberArgsException {

        this.squadreIscritte = new ArrayList<>();
        this.squadreIscritte.add(new Squadra("Italia", new BandieraFasceVerticali(Color.RED, Color.WHITE, Color.GREEN )));
        this.squadreIscritte.add(new Squadra("Austria", BandieraFasceOrizzontali::new, Color.RED, Color.WHITE, Color.RED));
        this.squadreIscritte.add(new Squadra("Francia", BandieraFasceVerticali::new, Color.RED, Color.WHITE, Color.BLUE));
        this.squadreIscritte.add(new Squadra("Germania", BandieraFasceOrizzontali::new, Color.RED, Color.YELLOW, Color.BLACK));
        this.squadreIscritte.add(new Squadra("Olanda", BandieraFasceOrizzontali::new, Color.RED, Color.WHITE, Color.BLUE));
        this.squadreIscritte.add(new Squadra("Bulgaria", BandieraFasceOrizzontali::new, Color.WHITE, Color.GREEN, Color.RED));
        this.squadreIscritte.add(new Squadra("Russia", BandieraFasceOrizzontali::new, Color.WHITE, Color.RED, Color.BLUE));
        this.squadreIscritte.add(new Squadra("Spagna", BandieraFasceOrizzontali::new, Color.RED, Color.YELLOW, Color.RED));
        this.squadreIscritte.add(new Squadra("Svezia", BandieraCroce::new, Color.YELLOW, Color.BLUE));
        this.squadreIscritte.add(new Squadra("Belgio", BandieraFasceVerticali::new, Color.RED, Color.YELLOW, Color.BLACK));
        this.squadreIscritte.add(new Squadra("Irlanda", BandieraFasceOrizzontali::new, Color.RED, Color.WHITE, Color.LIGHTGREEN));
        this.squadreIscritte.add(new Squadra("Ungheria", BandieraFasceOrizzontali::new, Color.RED, Color.WHITE, Color.GREEN));
        this.squadreIscritte.add(new Squadra("Finlandia", BandieraCroce::new, Color.BLUE, Color.WHITE));
        this.squadreIscritte.add(new Squadra("Danimarca", BandieraCroce::new, Color.WHITE, Color.RED));
        this.squadreIscritte.add(new Squadra("Polonia", BandieraFasceOrizzontali::new, Color.WHITE, Color.RED));
        this.squadreIscritte.add(new Squadra("Ucraina", BandieraFasceOrizzontali::new, Color.BLUE, Color.YELLOW));
        Collections.shuffle(this.squadreIscritte);

    }

    private void initGironi(){

        this.gironi = new ArrayList<>();
        for( int i = 0; i < this.squadreIscritte.size(); i+=N_SQUADRE_GIRONE){

            Squadra[] squadre = new Squadra[N_SQUADRE_GIRONE];
            for( int j = 0; j < N_SQUADRE_GIRONE; j++ )
                squadre[j] = this.squadreIscritte.get(i+j);
            this.gironi.add(new Girone(squadre, new Giornata[]{Giornata.PRIMA, Giornata.SECONDA, Giornata.TERZA}));

        }

    }


    private void popolaGridPane(){

        this.tabella.getChildren().clear();
        for( int i = 0; i < this.gironi.size(); i++ ){

            Girone girone = this.gironi.get(i);
            ArrayList<Partita> partite = girone.getPartite();
            VBox root = new VBox();
            root.setPrefWidth(300);
            root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
            int j = 0;
            for( Partita partita : partite ){

                if( j % 2 == 0 ){
                    root.getChildren().add(new Text(" ===== Giornata " + (j/2+1) + " ===== \n"));
                }
                Text text = new Text(partita.getSquadra1().getNome() + " - " + partita.getSquadra2().getNome());
                if( partita.isDisputata())
                    text.setText(text.getText() + " ----- " +partita.getGol1() + ":" +partita.getGol2());
                root.getChildren().add(text);
                j++;

            }

            root.getChildren().add(new Text("\n\n ======  Classifica ===== \n\n"));

            VBox classifica = new VBox();
            classifica.setSpacing(10);

            for( Squadra squadra : girone.getClassifica() ){

                Text punteggio = new Text(""+squadra.getPunteggio());
                Text nome = new Text(squadra.getNome());
                HBox riga = new HBox(punteggio, squadra.getBandiera(), nome);
                riga.setSpacing(20);
                classifica.getChildren().add(riga);

            }

            root.getChildren().add(classifica);

            if( i%(gironi.size()/2) == 0 )
                root.setBackground(new Background(new BackgroundFill(Color.WHITE, null,null)));
            else
                root.setBackground(new Background(new BackgroundFill(Color.YELLOW, null,null)));
            this.tabella.add(root, i%(gironi.size()/2), i/(gironi.size()/2));

        }

    }

    private void showAnimationStage(EventHandler<ActionEvent> handler){

        Circle ball = new Circle(20, Color.LIGHTBLUE);
        ball.setCenterX(60);
        ball.setCenterY(220);
        Pane pane = new Pane(ball);
        pane.setPrefSize(500,300);

        SequentialTransition transition = Animation.createAnimationStage(ball);

        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
        transition.setOnFinished((event) ->{
            stage.close();
            handler.handle(event);
        });
        transition.play();
        stage.show();


    }


}
