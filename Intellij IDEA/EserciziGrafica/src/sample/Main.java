package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.InputEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        GridPane griglia = ((GridPane) root);
        for( int i = 0; i < 9; i++ )
            for( int j = 0; j < 10; j++ ){
                final int I = i, J = j;
                Button btn = new Button(String.valueOf((i*10)+(j+1)));
                EventHandler<ActionEvent> evtHandler = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(I + " " + J);
                    }
                };
                btn.addEventHandler(ActionEvent.ACTION, evtHandler);
                griglia.add(btn, j, i);
            }
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
