package scacchiera;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

import javax.swing.*;

public class Casella extends Button {

    public static final int MAX_CLICKS = 4;

    private int numeroClick;

    public Casella( Paint background ){

        super();
        this.setBackground(new Background(new BackgroundFill(background, null, null)));
        this.numeroClick = 0;

    }

    public int getNumeroClick() {
        return numeroClick;
    }

    public void click(){

        this.numeroClick++;
        if( numeroClick >= MAX_CLICKS )
            this.setDisable(true);

    }

    public void setBackground( Paint paint ){

        this.setBackground(new Background(new BackgroundFill(paint, null, null)));

    }

    public void setOnAction(CasellaHandler casellaHandler){

        this.addEventHandler(ActionEvent.ACTION, casellaHandler.handler);

    }

    public String toString(){

        return "Casella[ Stato: " + (this.numeroClick%2==0 ? "Primo" : "Secondo") + " ]";

    }
}

