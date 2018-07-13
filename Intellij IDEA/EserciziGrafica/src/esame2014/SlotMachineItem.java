package esame2014;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.Random;

public enum SlotMachineItem {

    RECTANGLE(() ->new Rectangle(30, 90, Color.BLUE), "R"),
    CIRCLE( () ->new Circle(15, Color.BLUE), "C"),
    SQUARE( ()->new Rectangle(30, 30, Color.BLUE), "S"),
    TEXT( ()->new Text("Star"), "T" );

    private ShapeGenerator shapeGenerator;
    private String id;

    SlotMachineItem(ShapeGenerator shapeGenerator, String id){

        this.shapeGenerator = shapeGenerator;
        this.id = id;

    }

    public Shape getShape(){

        Shape res= this.shapeGenerator.getShape();
        res.setId(this.id);
        return res;

    }

    public static SlotMachineItem getRandomItem(){

        SlotMachineItem[] items = SlotMachineItem.values();
        Random rand = new Random();
        return items[rand.nextInt(items.length)];

    }


}
