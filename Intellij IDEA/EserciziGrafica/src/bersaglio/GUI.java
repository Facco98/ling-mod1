package bersaglio;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    public static final int PANE_WIDTH = 400;
    public static final int PANE_HEIGHT = 400;

    public static final int INITIAL_RADIUS = 45;
    public static final int INITIAL_SCORE = 0;

    public static final int N_CIRCLES = 4;

    public static final int MIN_SCORE = 1;
    public static final int MUL = 10;

    public static Color[] colors ={ Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};

    private TextField txtInput, txtOutput;
    private Group tabellone;
    private Button btnReset;

    private ArrayList<Circle> circles;

    private TextArea logArea;


    private void initGUI( int punteggio, double r ){


        this.txtInput = new TextField();
        this.txtOutput = new TextField();
        this.txtOutput.setDisable(true);
        this.resetGUI(punteggio, r);
        this.btnReset = new Button("Reset");
        this.logArea = new TextArea();
        this.logArea.setDisable(true);



    }

    private void resetGUI( int punteggio, double r ){

        this.tabellone = new Group();
        this.txtOutput.setText("" + punteggio);
        this.txtInput.setText(""+ r);
        this.generateCircles(r);
        Pane pane = new StackPane();
        pane.getChildren().addAll(this.circles);
        int i = this.circles.size();
        this.tabellone.getChildren().add(pane);
        this.tabellone.addEventHandler(MouseEvent.MOUSE_CLICKED, new CircleEventHandler(this.tabellone, 0, this.txtOutput));

    }

    public static void main(String[] args){

        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI(INITIAL_SCORE, INITIAL_RADIUS);


        this.btnReset.setOnAction((event ->{

            logArea.setText(logArea.getText() + txtOutput.getText() + "\n");
            resetGUI(INITIAL_SCORE, Double.parseDouble(txtInput.getText()));
            primaryStage.setScene(generateScene());

        }));

        Stage logStage = new Stage();
        this.logArea.setPrefSize(200,200);
        Scene logScene = new Scene(new StackPane(this.logArea));
        logStage.setScene(logScene);
        logStage.show();

        primaryStage.setScene(generateScene());
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest((event -> {

            System.exit(0);

        }));

        primaryStage.show();


    }

    private Scene generateScene(){

        HBox controls = new HBox( this.btnReset, this.txtInput, this.txtOutput);
        for( Node i : controls.getChildren() )
            HBox.setHgrow(i, Priority.ALWAYS);
        controls.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(controls,this.tabellone);
        vbox.setFillWidth(true);
        vbox.setLayoutX(0);
        vbox.setLayoutY(0);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vbox);
        vbox.setPrefSize(400, 400);
        return scene;
    }

    private void generateCircles(double r){

        this.circles = new ArrayList<>(N_CIRCLES);
        double score = MIN_SCORE;
        for( int i = N_CIRCLES; i > 0; i -- ) {

            Circle circle = new Circle((i)*r);
            circle.setFill(colors[i%colors.length]);
            circle.setStroke(Color.BLACK);
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new CircleEventHandler(this.tabellone, score, txtOutput));
            this.circles.add(circle);
            score = score*MUL;

        }

    }


}

class CircleEventHandler implements EventHandler<MouseEvent>{

    private Group tabellone;
    private double score;
    private TextField txtField;

    public CircleEventHandler( Group tabellone, double score, TextField txtField ){

        this.tabellone = tabellone;
        this.score = score;
        this.txtField = txtField;
    }

    @Override
    public void handle(MouseEvent event) {

        double mouseX = event.getSceneX() - this.tabellone.getLayoutX();
        double mouseY = event.getSceneY() - this.tabellone.getLayoutY();
        Shape line1 = new Line(mouseX-5, mouseY-5, mouseX+5, mouseY+5);
        Shape line2 = new Line(mouseX-5, mouseY+5, mouseX+5, mouseY-5);

        Shape cross = Shape.union(line1, line2);
        cross.setFill(Color.VIOLET);
        double newScore = Double.parseDouble(txtField.getText()) + score;
        txtField.setText(""+newScore);
        this.tabellone.getChildren().add(cross);



    }
}
