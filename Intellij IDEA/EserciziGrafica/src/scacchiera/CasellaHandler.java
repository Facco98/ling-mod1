package scacchiera;

import javafx.event.EventHandler;

import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import javax.swing.*;

public class CasellaHandler {

    private static final Paint[] COLORI_TIPO_1 = new Color[]{ GUI.colors[0], GUI.colors[1]};
    private static final Paint[] COLORI_TIPO_2 = new Color[]{ GUI.colors[2], GUI.colors[3]};



    public final EventHandler<ActionEvent> handler;

    private CasellaHandler( EventHandler<ActionEvent> handler ){

        this.handler = handler;

    }

    public static CasellaHandler getHandlerTipo1(Casella casella){

        return new CasellaHandler((event) -> {

            casella.click();
            casella.setBackground(COLORI_TIPO_1[casella.getNumeroClick()%2]);

        });

    }

    public static CasellaHandler getHandlerTipo2(Casella casella){

        return new CasellaHandler( (event) ->{

            casella.click();
            if( casella.getNumeroClick()%2==1) {
                Ellipse ellipse = new Ellipse(30, 15);
                ellipse.setFill(COLORI_TIPO_2[1]);
                casella.setGraphic(ellipse);
            } else
                casella.setGraphic(null);

        });

    }



}
