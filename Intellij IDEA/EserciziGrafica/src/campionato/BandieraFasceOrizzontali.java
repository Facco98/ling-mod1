package campionato;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BandieraFasceOrizzontali extends Bandiera {

    private Paint[] colori;

    public BandieraFasceOrizzontali(Paint... colori ) {

        this.colori = colori;
        this.generaBandiera();

    }

    public void generaBandiera() {

        for( int i = 0; i < colori.length; i++ ){

            Rectangle rectangle = new Rectangle(this.getPrefWidth(), this.getPrefHeight()/colori.length);
            rectangle.setFill(this.colori[i]);
            rectangle.setLayoutX(0);
            rectangle.setLayoutY(i*this.getPrefHeight()/colori.length);
            this.getChildren().add(rectangle);

        }


    }
}
