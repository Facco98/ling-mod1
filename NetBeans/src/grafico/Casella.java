/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author claudiofacchinetti
 */
public class Casella extends StackPane{
    
    public static final int RAGGIO = 2;
    
    private int r, c;
    
    public Casella(int r, int c){
        
        this.r = r;
        this.c = c;
        
    }
    
    public int getR(){
        
        return this.r;
        
    }
    
    public int getC(){
        
        return this.c;
        
    }
    
    public void aggiungiCerchio(){
        
        this.getChildren().clear();
        Circle circle = new Circle(RAGGIO, Color.BLUE);
        this.getChildren().add(circle);
        
    }
    
    public void rimuoviCerchio(){
        
        this.getChildren().clear();
        
    }
    
    public boolean vuota(){
        
        return this.getChildren().isEmpty();
        
    }
    
}
