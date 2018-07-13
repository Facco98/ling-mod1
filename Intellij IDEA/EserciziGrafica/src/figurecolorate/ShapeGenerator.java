package figurecolorate;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public enum ShapeGenerator {

    QUADRATO(() ->{
        return new Rectangle(50,50);
    }),
    CERCHIO(() ->{

        return new Circle(25);

    }),
    ROMBO(() ->{

        Rectangle rect = new Rectangle(50,50);
        rect.getTransforms().add(new Rotate(45, rect.getWidth()/2, rect.getHeight()/2));
        return rect;
    });

    private Generator generator;

    ShapeGenerator( Generator generator ){

        this.generator = generator;

    }

    public Generator getGenerator(){

        return this.generator;

    }

    public static Shape getShape(int i){

        return ShapeGenerator.values()[i%ShapeGenerator.values().length].generator.generate();

    }


}

interface Generator{

    public Shape generate();

}