package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GUI extends Application {

    private Text title;
    private ArrayList<TextField> inOperands;
    private Text result;

    private ArrayList<Button> commands;

    private Text copyright;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Creating the elements

        this.title = new Text("Calculator");
        this.title.setFont(Font.font(36));

        this.inOperands = new ArrayList<>();

        for( int i  = 0; i < Operation.getMaxNOperands(); i++ ){

            TextField tmp = new TextField();
            tmp.setAlignment(Pos.CENTER);
            this.inOperands.add(tmp);

        }

        this.result = new Text("");

        this.commands = new ArrayList<>();

        this.copyright = new Text("© magicSoftware");
        this.copyright.setFont(Font.font("Bodoni 72", FontPosture.ITALIC, 20));

        // Create buttons and event handlers.

        for( Operation op: Operation.values() ) {

            Button tmp = new Button(op.toString());
            tmp.addEventHandler(ActionEvent.ACTION, EventHandler -> {

                try {

                    Double[] params = new Double[op.getnOperands()];
                    for( int i = 0; i < op.getnOperands(); i++ ) {
                        params[i] = Double.parseDouble(inOperands.get(i).getText());
                    }
                    Double res = op.calculate(params);
                    result.setText(res.toString());
                } catch (DividingByZeroException e) {
                    result.setText("You can't divide by zero");
                } catch (Exception ex) {
                    result.setText("Be sure you inserted real numbers");
                }

            });

            tmp.setPrefWidth(100);

            this.commands.add(tmp);
        }

        // Arraging the area for the buttons;

        FlowPane buttonArea = new FlowPane(Orientation.HORIZONTAL);
        buttonArea.getChildren().addAll(this.commands);
        buttonArea.setHgap(0);
        buttonArea.setAlignment(Pos.CENTER);


        // Creating and spacing boxes for the operands
        Collection<VBox> vboxes = new ArrayList<>();
        int i = 0;

        VBox central = new VBox();

        for( TextField field : this.inOperands ){

            VBox vbox = new VBox(new Text("Operand :"+(i+1)), field );
            vbox.setSpacing(5);
            central.getChildren().add(vbox);
            i++;

        }
        central.getChildren().addAll(buttonArea, this.result);
        central.setAlignment(Pos.CENTER);
        central.setSpacing(15);

        // Create the root BorderPane

        BorderPane root = new BorderPane();
        root.setCenter(central);
        root.setTop(this.title);
        root.setLeft(new Text("left"));
        root.setRight(new Text("Right"));
        root.setBottom( this.copyright );


        // Setting constraints for central item of BorderPane
        BorderPane.setMargin(root.getCenter(), new Insets(10,0,0,0) );

        // Setting constraints for left item of BorderPane
        BorderPane.setAlignment(root.getLeft(), Pos.CENTER_LEFT);
        BorderPane.setMargin(root.getLeft(), new Insets(0,80,0,0));

        // Setting constraints for right item of BorderPane
        BorderPane.setAlignment(root.getRight(), Pos.CENTER_RIGHT);
        BorderPane.setMargin(root.getRight(), new Insets(0,40,0,40));

        // Setting constraints for upper item of BorderPane
        BorderPane.setAlignment(root.getTop(), Pos.CENTER);

        // Setting constraints for lower item of BorderPane
        BorderPane.setAlignment(root.getBottom(), Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Calculator in Java");
        primaryStage.show();


    }

    public static void main(String[] args){

        Application.launch(args);


    }

    private void f(Operation x){

        System.out.println(x);

    }
}
