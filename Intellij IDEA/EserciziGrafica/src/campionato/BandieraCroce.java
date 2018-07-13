package campionato;

import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class BandieraCroce extends Bandiera {

    public BandieraCroce(Paint... colori){

        super();
        Line verticalLine = new Line(this.getPrefWidth()/4, 0, this.getPrefWidth()/4, this.getPrefHeight());
        Line horizzontalLine = new Line(0, this.getPrefHeight()/2, this.getPrefWidth(), this.getPrefHeight()/2);
        Shape cross = Shape.union(verticalLine, horizzontalLine);
        cross.setStroke(colori[0]);
        cross.setLayoutX(0);
        cross.setLayoutY(0);
        this.getChildren().add(cross);
        this.setBackground(new Background(new BackgroundFill(colori[1], null,null)));



    }

}
