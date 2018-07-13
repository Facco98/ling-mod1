package campionato;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BandieraFasceVerticali extends Bandiera {

    public BandieraFasceVerticali(Paint... colori ) throws WrongNumberArgsException {

        super();
        if( colori.length == 0 )
            throw new WrongNumberArgsException("Non hai specificato colori");
        int n = colori.length;
        for( int i  = 0; i < colori.length; i++ ){

            Rectangle rect = new Rectangle(this.getPrefWidth()/n, this.getPrefHeight());
            rect.setLayoutY(0);
            rect.setLayoutX(i*this.getPrefWidth()/n);
            rect.setFill(colori[i]);
            this.getChildren().add(rect);

        }


    }

    public BandieraFasceVerticali( BandieraFasceVerticali bandiera ){

        super(bandiera);

    }

}
