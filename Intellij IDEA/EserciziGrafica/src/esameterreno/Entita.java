package esameterreno;

import javafx.scene.shape.Shape;

public abstract class Entita {

    Shape shape;

    private int x;
    private int y;

    public Entita(Shape shape, int x, int y){

        this.shape = shape;
        this.x = x;
        this.y = y;

    }

    public Shape getShape(){

        return this.shape;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
