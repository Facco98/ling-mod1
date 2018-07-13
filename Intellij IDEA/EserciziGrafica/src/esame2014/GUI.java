package esame2014;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;

public class GUI extends Application {

    public static final int PUNTEGGIO_LIMITE = 100;

    private int credito;
    private int punteggio;
    private int coins;

    private Text creditLabel;
    private Text punteggioLabel;

    private Button newGameButton;
    private Button spinButton;
    private Button payButton;

    private ArrayList<Button> roulettes;
    private Button coinsButton;

    @Override
    public void start(Stage primaryStage){

        this.credito = 0;
        this.punteggio = 0;
        this.coins = 6;
        this.roulettes = new ArrayList<>();

        this.spinButton = new Button("Spin");
        this.payButton = new Button("Pay");

        Text title = new Text("SLOTS");
        title.setFont(Font.font(25));
        title.setStroke(Color.RED);

        TilePane roulettesArea = new TilePane();
        roulettesArea.setHgap(50);
        roulettesArea.setAlignment(Pos.CENTER);

        for( int i = 0; i < 3; i++ ){

            Button roulette = new Button();
            roulette.setId("slot"+i);
            roulette.setGraphic(SlotMachineItem.getRandomItem().getShape());
            roulette.setPrefWidth(50);
            this.roulettes.add(roulette);
            roulette.setPrefHeight(100);
            roulette.setOnAction((event -> {

                punteggio /= 2;
                updatePunteggioLabel();
                updateRoulette(roulette);
                winCheck();

            }));

        }

        roulettesArea.getChildren().addAll(this.roulettes);

        this.creditLabel = new Text();
        this.creditLabel.textProperty().addListener((observable, oldValue, newValue) ->{

            if( credito == 0 ) {
                spinButton.setDisable(true);
                payButton.setDisable(true);
            } else{

                spinButton.setDisable(false);
                spinButton.setDisable(false);

            }

        });

        this.updateCreditoLabel();
        this.punteggioLabel = new Text();

        this.punteggioLabel.textProperty().addListener((observable, oldValue, newValue) -> {

            if( punteggio == 0 ) {
                roulettes.forEach((roulette) -> roulette.setDisable(true));
                spinButton.setDisable(true);
            }
            else {
                roulettes.forEach((roulette) -> roulette.setDisable(false));
                spinButton.setDisable(false);
            }
        });

        this.updatePunteggioLabel();


        HBox labelsArea = new HBox(creditLabel, punteggioLabel);
        labelsArea.setSpacing(30);
        labelsArea.setAlignment(Pos.CENTER);


        this.newGameButton = new Button("Nuova partita");
        this.newGameButton.setOnAction((event) ->{

            if( credito < PUNTEGGIO_LIMITE ){

                createTextStage("Non hai abbastanza crediti");
            } else{

                credito -= 100;
                punteggio = 128;
                updateCreditoLabel();
                updatePunteggioLabel();
            }

        });

        this.spinButton.setOnAction((event) ->{

            this.punteggio /= 2;
            updatePunteggioLabel();
            updateRoulettes();
            winCheck();

        });

        this.payButton.setOnAction((event -> {

            createTextStage("Hai vinto " + (float)credito/(float)100 + " Euro!!");
            reset();

        }));

        HBox controlsArea = new HBox(this.newGameButton, this.payButton, this.spinButton);
        controlsArea.setSpacing(30);
        controlsArea.setAlignment(Pos.CENTER);

        VBox slot = new VBox(title, roulettesArea, labelsArea, controlsArea);
        slot.setSpacing(30);
        slot.setAlignment(Pos.CENTER);



        this.coinsButton = new Button();
        this.updateCoins();

        HBox body = new HBox(slot, this.coinsButton);
        Group root = new Group(body);

        this.coinsButton.setOnAction((event -> {

            if( coins > 0 ){

                double x = (coinsButton.getLayoutX() + coinsButton.getWidth()/2);
                double y = (coinsButton.getLayoutY() + coinsButton.getHeight()/2);
                double arrivalX = (title.getLayoutX() + title.getWrappingWidth()/2);
                double arrivalY = y;


                coins--;
                credito += 100;
                updateCreditoLabel();

                System.out.println(x);
                System.out.println(y);
                System.out.println(arrivalX);
                System.out.println(arrivalY);
                Circle circle = new Circle(10, Color.BLUE);
                circle.setOpacity(0.8);
                root.getChildren().add(circle);
                TranslateTransition transition = new TranslateTransition(Duration.seconds(1), circle);
                transition.setFromX(x);
                transition.setFromY(y);
                transition.setToX(arrivalX);
                transition.setToY(arrivalY);
                transition.play();
                transition.setOnFinished((event1) -> {root.getChildren().remove(circle);});
            }
            updateCoins();

        }));


        body.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene( scene );
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    private void updateCreditoLabel(){

        this.creditLabel.setText("Credito: "+this.credito);

    }

    private void updatePunteggioLabel(){

        this.punteggioLabel.setText("Punteggio: "+this.punteggio);

    }

    private void updateRoulettes(){

        this.roulettes.forEach((roulette) -> {

            updateRoulette(roulette);
        });

    }

    private void updateRoulette(Button roulette){

        Shape shape = SlotMachineItem.getRandomItem().getShape();
        FadeTransition transition = new FadeTransition(Duration.seconds(2), roulette);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
        roulette.setGraphic(shape);

        transition.setFromValue(0);
        transition.setToValue(1);
        transition.playFromStart();
        System.out.println(shape);

    }

    private void winCheck(){

        boolean uguale = true;
        for( int i = 0; i < this.roulettes.size()-1 && uguale; i++ )
            if( !this.roulettes.get(i).getGraphic().getId().equals( this.roulettes.get(i+1).getGraphic().getId()) )
                uguale=false;
        if( uguale ){

            credito += punteggio * 100;
            updateCreditoLabel();
            punteggio = 0;
            updatePunteggioLabel();
            createTextStage("Hai vinto");

        }
    }

    private void createTextStage( String text ){

        Stage stage = new Stage();
        Text t = new Text(text);
        stage.setScene(new Scene(new StackPane(t)));
        stage.sizeToScene();
        stage.show();

    }

    private void reset(){

        this.punteggio = 0;
        this.credito = 0;
        this.coins = 3;
        updatePunteggioLabel();
        updateCreditoLabel();
        updateCoins();

    }

    private void updateCoins(){
        if( this.coins > 0 ) {
            ArrayList<Shape> coinShapes = new ArrayList<>(this.coins);
            Circle previous = new Circle(30, 30, 10);
            coinShapes.add(previous);
            for (int i = 1; i < this.coins; i++) {

                Circle coin = new Circle(30 - 3 * i, 30 - 3 * i, 10);
                Shape res = Shape.subtract(coin, previous);
                res.setTranslateX(-3 * i);
                res.setTranslateY(-3 * i);
                previous = coin;
                coinShapes.add(res);

            }

            Shape res = coinShapes.get(0);
            for (int i = 1; i < coinShapes.size(); i++)
                res = Shape.union(res, coinShapes.get(i));
            res.setFill(Color.BLUE);
            this.coinsButton.setGraphic(res);

            } else{
            this.coinsButton.setGraphic(new Text("No more coins"));
        }

    }

    public static void main( String[] args ){

        launch( args );

    }
}
