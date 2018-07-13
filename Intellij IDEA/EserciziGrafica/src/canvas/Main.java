package canvas;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Main extends Application {

    private final int width = 1000;
    private final int height = 1000;

    private boolean pressed = true;

    private LinkedList<Line> lines;
    private int startX;
    private int startY;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {

        canvas = new Canvas();
        this.lines = new LinkedList<>();
        canvas.setWidth(this.width);
        canvas.setHeight(this.height);
        canvas.getGraphicsContext2D().setStroke(Color.BLUE);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            if( !pressed ) {
                startX = (int) event.getX();
                startY = (int) event.getY();
                pressed = true;
            }

        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {

            if( pressed ){

                if( this.lines.size() > 0 )
                    this.lines.removeLast();
                this.lines.add(new Line(startX, startY, event.getX(), event.getY()));
                render();

            }

        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event ->{

            if( pressed ){

                this.lines.add(new Line(startX, startY, event.getX(), event.getY()));
                render();
                this.pressed = false;

            }

        });


        Scene scene = new Scene(new Group(canvas));

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->{

            if( event.isControlDown() && event.getCode().equals(KeyCode.C) ){

                lines = new LinkedList<>();
                render();

            }

        });


        scene.setFill(Color.AQUAMARINE);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Canvas is funny");
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static void main( String[] args ){

        launch(args);

    }

    public void render(){

        this.canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        this.lines.forEach((Line a) ->{

            canvas.getGraphicsContext2D().strokeLine(a.getStartX(), a.getStartY(), a.getEndX(), a.getEndY());

        });

    }
}
