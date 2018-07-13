package campionato;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public abstract class Bandiera extends Pane {

    public static final int DEFAULT_WIDTH = 70, DEFAULT_HEIGHT = 26;
    public static final int BORDER = 2;

    protected Bandiera(){

        this.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        BorderStrokeStyle style = new BorderStrokeStyle(StrokeType.OUTSIDE, StrokeLineJoin.BEVEL, StrokeLineCap.SQUARE, 4, 2, null);
        Border border = new Border(new BorderStroke(Color.BLACK, style, null, new BorderWidths(2)));
        this.setBorder(border);

    }

    protected Bandiera( Bandiera bandiera ){

        this.setPrefSize( bandiera.getPrefWidth(), bandiera.getPrefHeight() );
        this.setBorder(bandiera.getBorder());

    }


}
