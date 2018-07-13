package bottonecerchio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;


public class Applicazione extends Application {


    @Override
    public void start(Stage primaryStage) {


        Circle circle = new Circle(130,140,30, CircleManager.DEFAULT_COLOR);
        Button btn = new Button("Muovi il cerchio");
        Button cambiaDirezione = new Button("Inverti il movimento");
        cambiaDirezione.setLayoutX(200);
        Button terminaProgramma = new Button("Termina il programma");
        terminaProgramma.setLayoutX(450);
        terminaProgramma.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });


        Parent root = new Group(circle);
        Scene scene = new Scene(root);



        CircleManager circleManager = new CircleManager(circle, primaryStage, (Group) root, 10);

        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, new CircleEventHandler(circleManager));
        btn.addEventHandler(ActionEvent.ACTION, new MoveButtonEventHandler(circleManager));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyboardEventHandler(circleManager));
        cambiaDirezione.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                circleManager.toggleHorizontal();
            }
        });


        primaryStage.setScene(scene);
        primaryStage.setWidth(285);
        primaryStage.setHeight(315);
        primaryStage.setTitle("Gioco del cerchio");
        primaryStage.show();


        Stage secondaFinestra = new Stage();
        Group gruppoSecondaFinestra = new Group(btn,terminaProgramma,cambiaDirezione );
        secondaFinestra.setScene(new Scene(gruppoSecondaFinestra));
        secondaFinestra.setTitle("Controlli");
        secondaFinestra.show();


    }


    public static void main( String[] args ){

        Application.launch(args);

    }




}
