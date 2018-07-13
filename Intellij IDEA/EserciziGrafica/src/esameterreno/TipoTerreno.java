package esameterreno;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum TipoTerreno {

    PRATO(Color.GREEN, false),
    STRADA(Color.GRAY, true);


    private Paint paint;
    private boolean isOccupabile;

    TipoTerreno(Paint paint, boolean isOccupabile){

        this.paint = paint;
        this.isOccupabile = isOccupabile;

    }

    public Paint getBackground() {

        return this.paint;

    }

    public boolean isOccupabile(){

        return this.isOccupabile;

    }


}
