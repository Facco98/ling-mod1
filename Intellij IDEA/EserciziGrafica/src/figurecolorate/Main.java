package figurecolorate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private Pane spazioGrafico;
    private Button btnClear, btnClearLast, btnClearFirst, btnPrint;
    private Text lblC1, lblC2, lblSfondo;
    private TextField txtC1, txtC2, txtSfondo;
    private Paint C1, C2, backgroundColor;

    private ArrayList<Shape> shapes;

    private void initGUI(){

        this.shapes = new ArrayList<>();

        this.spazioGrafico = new Pane();
        this.btnClear = new Button("Clear");
        this.btnClearFirst = new Button("Clear-First");
        this.btnClearLast = new Button("Clear-Last");
        this.btnPrint = new Button("Print");
        this.lblC1 = new Text("C1");
        this.lblC2 = new Text("C2");
        this.lblSfondo = new Text("Sfondo");
        this.txtC1 = new TextField("ORANGE");
        this.txtC2 = new TextField("GREEN");
        this.txtSfondo = new TextField("RED");

        this.C1 = Color.valueOf(txtC1.getText());
        this.C2 = Color.valueOf(txtC2.getText());
        this.backgroundColor = Color.valueOf(txtSfondo.getText());
        this.spazioGrafico.setBackground( new Background(new BackgroundFill(this.backgroundColor, null,null)));
        this.spazioGrafico.setPrefSize(400,400);

        this.btnClear.setOnAction((event) ->{
            this.spazioGrafico.getChildren().clear();
        });

        this.btnClearFirst.setOnAction((event -> {

            if( this.spazioGrafico.getChildren().size() > 0 )
                this.spazioGrafico.getChildren().remove(0);

        }));

        this.btnClearLast.setOnAction((event -> {

            if( this.spazioGrafico.getChildren().size() > 0 )
                this.spazioGrafico.getChildren().remove(this.spazioGrafico.getChildren().size()-1);

        }));

        this.txtC1.setOnAction((event ->{

            try {
                Color c = Color.valueOf(txtC1.getText());
                this.C1 = c;
            } catch ( Exception ex ){

            }

        }));

        this.txtC2.setOnAction((event ->{

            try {
                Color c = Color.valueOf(txtC2.getText());
                this.C2 = c;
            } catch ( Exception ex ){

            }
        }));

        this.txtSfondo.setOnAction((event ->{

            try {
                Color c = Color.valueOf(txtSfondo.getText());
                this.backgroundColor = c;
                this.spazioGrafico.setBackground(new Background(new BackgroundFill(this.backgroundColor, null, null)));
            } catch ( Exception ex ){

            }

        }));

        this.spazioGrafico.setOnMouseClicked((event) ->{

            Shape shape;
            double mouseX = event.getX();
            double mouseY = event.getY();

            if( this.spazioGrafico.getChildren().size() == 0 ){

                shape = ShapeGenerator.getShape(0);
                shape.setFill(Color.BLACK);
                shape.setLayoutX(mouseX);
                shape.setLayoutY(mouseY);

            } else{

                Shape last = (Shape) this.spazioGrafico.getChildren().get(this.spazioGrafico.getChildren().size()-1);
                Paint color;
                if( mouseX >= last.getLayoutX() )
                    color = this.C1;
                else
                    color = this.C2;
                shape = ShapeGenerator.getShape(this.spazioGrafico.getChildren().size());
                shape.setFill(color);
                shape.setLayoutX(mouseX);
                shape.setLayoutY(mouseY);


            }



            if( shape.getLayoutBounds().getWidth() + shape.getLayoutX() < this.spazioGrafico.getWidth())
             this.spazioGrafico.getChildren().add(shape);
        });

        this.btnPrint.setOnAction((event) ->{

            Stage stage = new Stage();
            TextArea logArea = new TextArea();
            logArea.setEditable(false);
            logArea.setPrefSize(400,300);
            this.spazioGrafico.getChildren().forEach((elem) ->{

                logArea.setText(logArea.getText() + elem.toString()+ "\n");

            });
            stage.setScene(new Scene(logArea));
            stage.sizeToScene();
            stage.show();

        });



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI();
        GridPane root;
        VBox controls = new VBox(this.btnClear, this.btnClearFirst, this.btnClearLast, new HBox(this.lblC1, this.txtC1), new HBox(this.lblC2, this.txtC2), new HBox(this.lblSfondo, this.txtSfondo), this.btnPrint);
        root = new GridPane();
        root.add(spazioGrafico, 0,0);
        root.setHgap(30);
        GridPane.setHgrow(this.spazioGrafico, Priority.ALWAYS);
        GridPane.setVgrow(this.spazioGrafico, Priority.ALWAYS);
        root.setOnKeyPressed(((evt) ->{

            if(evt.getCode().equals(KeyCode.C))
                this.btnClear.fireEvent(new ActionEvent());
            else if( evt.getCode().equals(KeyCode.L) )
                this.btnClearLast.fireEvent(new ActionEvent());
            else if( evt.getCode().equals(KeyCode.F))
                this.btnClearFirst.fireEvent(new ActionEvent());
            else if( evt.getCode().equals(KeyCode.P))
                this.btnPrint.fireEvent(new ActionEvent());
            evt.consume();

        }));

        primaryStage.setOnCloseRequest((evt) ->{

            System.exit(0);

        });

        primaryStage.setScene(new Scene(root));
        root.setPrefSize(750,750);
        primaryStage.show();

        Stage stageControlli = new Stage();
        stageControlli.setScene(new Scene(controls));
        stageControlli.sizeToScene();
        stageControlli.show();


    }

    public static void main(String[] args){

        Application.launch(args);

    }
}
