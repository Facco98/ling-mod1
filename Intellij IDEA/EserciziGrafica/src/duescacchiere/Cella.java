package duescacchiere;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

public class Cella extends Button {

    private int r,c;

    public Cella(int r, int c, Paint background){

        super();
        this.r = r;
        this.c = c;
        this.setBackground(background);

    }

    public void setBackground(Paint paint){

        this.setBackground(new Background(new BackgroundFill(paint, null, null)));

    }

    public int getRow(){

        return this.r;

    }

    public int getColumn(){

        return this.c;

    }

    public boolean isAnnerita(){

        return this.getBackground().getFills().get(0).getFill().equals(GUI.COLORE_CLICK);

    }



}
