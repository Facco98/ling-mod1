/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadi;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author claudiofacchinetti
 */
public class Dado extends Pane{
    
    private static final int RAGGIO = 15;
    private static final Paint COLORE = Color.BLACK;
    
    private ValoreDado valore;
    
    public Dado(ValoreDado valore){
        
        super();
        this.setPrefSize(150, 150);
        this.generaFaccia(valore);
        
    }
    
    private void generaFaccia(ValoreDado valore){
        
        this.valore = valore;
        Rectangle base = new Rectangle(150,150);
        base.setLayoutX(0);
        base.setLayoutY(0);
        base.setFill(Color.WHITE);
        base.setStroke(COLORE);
        this.getChildren().add(base);
        
        ArrayList<Circle> cerchi = new ArrayList<>();
        
        if( valore == ValoreDado.UNO || valore == ValoreDado.TRE || valore == ValoreDado.CINQUE ){
            Circle circle = new Circle(RAGGIO, COLORE);
            circle.setCenterX(this.getPrefWidth()/2);
            circle.setCenterY(this.getPrefHeight()/2);
            cerchi.add(circle);
        }
        
        if( valore != ValoreDado.UNO ){
            
            Circle[] circles = new Circle[]{
                
                new Circle(this.getPrefWidth()/2 -40 , this.getPrefHeight()/2 -40, RAGGIO, COLORE),
                new Circle(this.getPrefWidth()/2 + 40, this.getPrefHeight()/2 +40, RAGGIO, COLORE)
                
            };
            for( Circle c : circles )
               cerchi.add(c);
            
        }
        
        if( valore == ValoreDado.CINQUE  || valore == ValoreDado.QUATTRO || valore == ValoreDado.SEI){
            
            Circle[] circles = new Circle[]{
                
                new Circle(this.getPrefWidth()/2+40, this.getPrefHeight()/2-40, RAGGIO, COLORE),
                new Circle(this.getPrefWidth()/2-40, this.getPrefHeight()/2+40, RAGGIO, COLORE)
                
            };
            for( Circle c : circles )
                cerchi.add(c);
            
        }
        
        if( valore == ValoreDado.SEI ){
            
            Circle[] circles = new Circle[]{
                new Circle(this.getPrefWidth()/2, this.getPrefHeight()/2-40, RAGGIO, COLORE),
                new Circle(this.getPrefWidth()/2, this.getPrefHeight()/2+40, RAGGIO, COLORE),
                
            };
            for( Circle c : circles )
                cerchi.add(c);
         
        }
        
        this.getChildren().addAll(cerchi);
        
    }
    
    public void rigenera(ValoreDado valore){
        
        this.getChildren().clear();
        this.generaFaccia(valore);
        
    }
    
    public void rigeneraRandom(){
        
        int valoriPossibili = ValoreDado.values().length;
        ValoreDado valore = ValoreDado.values()[new Random().nextInt(valoriPossibili)];
        this.rigenera(valore);
        
    }
    
    public ValoreDado getValue(){
        
        return this.valore;
        
    }
   
}
