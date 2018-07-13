/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author claudiofacchinetti
 */
public class Main extends Application {
    
    public static final int DIMENSIONI_CAMPO = 500;
    
    private Pane campo;
    private Button btnSparisci, btnStampa, btnReset;
    private Text lblContatore;
    private int contatore;
    private boolean dissolvi;
    private ArrayList<Dado> dadi;
    
    private void initValori(){
        
        this.contatore = 10;
        this.dissolvi = false;
        this.dadi = new ArrayList<>();
        
    }
    
    private void initGui(){
        
        this.initValori();
        this.campo = new TilePane();
        this.campo.setPrefSize(DIMENSIONI_CAMPO, DIMENSIONI_CAMPO);
        this.campo.setBackground(new Background(new BackgroundFill(Color.GREEN, null,null)));
        this.btnSparisci = new Button();
        this.aggiornaBottoneSparisci();
        this.btnReset = new Button("Reset");
        this.btnStampa = new Button("Stampa");
        this.lblContatore = new Text("");
        this.aggiornaLabelContatore();
        
        this.btnReset.setOnAction((evt) ->{
            
            Animation animation = this.getAnimation();
            animation.setOnFinished((ev ->{
                
                this.initValori();
                this.aggiornaBottoneSparisci();
                this.aggiornaLabelContatore();
                this.aggiornaCampo();
                
            }));
            animation.play();
            
            
        });
        
        this.btnSparisci.setOnAction((evt ->{
            
            
            this.dissolvi = !this.dissolvi;
            this.aggiornaBottoneSparisci();
            
        }));
        
        this.campo.setOnMouseClicked((evt->{
            
            if( this.dadi.size() < 3 ){
                
                int valoriPossibili = ValoreDado.values().length;
                ValoreDado valore = ValoreDado.values()[new Random().nextInt(valoriPossibili)];
                Dado dado = new Dado(valore);
                dado.setOnMouseClicked((e ->{
                    
                    if( contatore > 0 ){
                        this.contatore--;
                        this.aggiornaLabelContatore();
                        dado.rigeneraRandom();
                        this.aggiornaCampo();
                        this.checkVictory();
                    }
                    else if( contatore == 0 )
                        new Alert(Alert.AlertType.INFORMATION, "Hai Perso", ButtonType.OK).show();
                    
                }));
                this.dadi.add(dado);
                this.aggiornaCampo();
                if( this.dadi.size() == 3 )
                    this.checkVictory();
                
            }
            
        }));
        
        this.btnStampa.setOnAction((evt ->{
            
            for( Dado d : this.dadi )
                System.out.println(d.getValue().val);
            
        }));
             
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        this.initGui();
        HBox controlli = new HBox(this.btnReset, this.btnSparisci, this.btnStampa, this.lblContatore);
        VBox root = new VBox(this.campo, controlli);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void aggiornaLabelContatore(){
        
        this.lblContatore.setText("" + this.contatore);
        
    }
    
    private void aggiornaBottoneSparisci(){
        
        if( this.dissolvi )
            this.btnSparisci.setText("Dissolvimento");
        else
            this.btnSparisci.setText("Scorrimento");
        
    }
    
    private void aggiornaCampo(){
        
        this.campo.getChildren().clear();
        for( Dado d : this.dadi )
            this.campo.getChildren().add(d);
        
    }

    private void checkVictory() {
        int c = ((5-1)-1)%6+1;
        ArrayList<Integer> values = new ArrayList<>();
        for( Dado d : this.dadi )
            values.add(d.getValue().val);
        Collections.sort(values);
        boolean continua = true;
        for( int i = 0; i < values.size()-1 && continua; i++ ){
            
            if( values.get(i) != values.get(i+1) -1 )
                continua = false;
            
        }
        if( continua )
            new Alert(AlertType.INFORMATION, "Hai vinto", ButtonType.OK).show();
    }
    
    private Animation getAnimation(){
        
        ParallelTransition animation = new ParallelTransition();
        if( this.dissolvi ){
            
           for( Dado d : this.dadi ){
               
               FadeTransition transition = new FadeTransition(Duration.seconds(2), d);
               transition.setFromValue(1);
               transition.setToValue(0);
               animation.getChildren().add(transition);
               
           }
           
            
        }
        else{
            
            for( Dado d: this.dadi){
                
                int dir = new Random().nextInt(4);
                TranslateTransition transition = new TranslateTransition(Duration.seconds(2), d);
                if( dir == 0 )
                    transition.setToX(-1000);
                else if( dir == 1 )
                    transition.setToY(-1000);
                else if( dir == 2 )
                    transition.setToX(1000);
                else if( dir == 3 )
                    transition.setToY(1000);
                animation.getChildren().add(transition);
            }
            
        }
        return animation;
        
    }
    
}
