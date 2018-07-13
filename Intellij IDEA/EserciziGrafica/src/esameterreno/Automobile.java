package esameterreno;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Automobile extends Entita {


    public Automobile(int x, int y) {
        super(new Circle(10, Color.RED), x, y );
    }

}
