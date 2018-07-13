package grafico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author claudiofacchinetti
 */
public class Main extends Application {
    
    public final static int N_CASELLE_RIGA = 80;
    public final static int CELLA_SIZE = 10;
    
    private GridPane tabella, riga;
    private ArrayList<Casella> caselleTabella, caselleRiga;
    private Button btnAllOn, btnAllOff, btnRandom, btnSin, btnCos, btnLinear;
    
    private void initGUI(){
        
        this.tabella = new GridPane();
        this.riga = new GridPane();
        this.caselleTabella = new ArrayList<>();
        this.caselleRiga = new ArrayList<>();
        this.btnAllOff = new Button("All Off");
        this.btnAllOn = new Button("All On");
        this.btnCos = new Button("Cos");
        this.btnSin  =new Button("Sin");
        this.btnRandom =  new Button("Random");
        this.btnLinear = new Button("Linear");
        this.initCaselleRiga();
        this.initCaselleTabella();
        this.popolaTabella();
        this.popolaRiga();
        
        this.btnAllOn.setOnAction((evt ->{
            
            for( Casella c : this.caselleRiga )
                c.aggiungiCerchio();
            
        }));
        
        this.btnAllOff.setOnAction((evt ->{
            
            for( Casella c : this.caselleRiga )
                c.rimuoviCerchio();
            
            for( Casella c : this.caselleTabella )
                c.rimuoviCerchio();
            
        }));
        
        this.btnRandom.setOnAction((evt ->{
            
            for( Casella c : this.caselleRiga ){
                
                c.rimuoviCerchio();
                if( Math.random() <= 0.5 )
                    c.aggiungiCerchio();
                
            }
            
        }));
        
        this.btnLinear.setOnAction((evt) ->{
            
            this.btnAllOff.fireEvent(new ActionEvent());
            this.btnRandom.fireEvent(new ActionEvent());
            for( int i = 0; i < this.caselleRiga.size(); i++ ){
                
                Casella casellaRiga = this.caselleRiga.get(i);
                if( !casellaRiga.vuota() )
                    ((Casella) this.getNodeAtIndexes(this.caselleRiga.size()-1-i, i)).aggiungiCerchio();
                
            }
            
        });
        
        this.btnSin.setOnAction((evt ->{
            
            this.btnAllOff.fireEvent(new ActionEvent());
            this.btnRandom.fireEvent(new ActionEvent());
            for( int i = 0; i < this.caselleRiga.size(); i++ ){
                
                Casella casellaRiga = this.caselleRiga.get(i);
                if( !casellaRiga.vuota() ){
                    
                    System.out.println(Math.sin(i/N_CASELLE_RIGA));
                    long indiceDiscretizzato = this.discretizza(Math.sin(i/9.0), -1, 1, N_CASELLE_RIGA);
                    System.out.println(indiceDiscretizzato);
                    System.out.println("==============");
                    ((Casella)this.getNodeAtIndexes(indiceDiscretizzato, i)).aggiungiCerchio();
                    
                }
                
            }
            
        }));
        
        this.btnCos.setOnAction((evt) ->{
            
            this.btnAllOff.fireEvent(new ActionEvent());
            this.btnRandom.fireEvent(new ActionEvent());
            for( int i = 0; i < this.caselleRiga.size(); i++ ){
                
                Casella casellaRiga = this.caselleRiga.get(i);
                if( !casellaRiga.vuota() ){
                    
                    System.out.println(Math.cos(i));
                    long indiceDiscretizzato = this.discretizza(Math.cos(i/9.0), -1, 1, N_CASELLE_RIGA);
                    System.out.println(indiceDiscretizzato);
                    System.out.println("==============");
                    ((Casella)this.getNodeAtIndexes(indiceDiscretizzato, i)).aggiungiCerchio();
                    
                }
                
            }
            
            
        });
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        this.initGUI();
        HBox controlli = new HBox(this.btnAllOff, this.btnAllOn, this.btnCos, this.btnSin, this.btnLinear, this.btnRandom);
        VBox root = new VBox(this.tabella, this.riga, controlli);
        root.setSpacing(20);
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
    
    
   private void initCaselleTabella(){
       
       this.caselleTabella.clear();
       for( int i  = 0; i < N_CASELLE_RIGA; i++ )
           for( int j  = 0; j < N_CASELLE_RIGA; j++ ){
               Casella casella = new Casella(i,j);
               casella.setPrefSize(CELLA_SIZE,CELLA_SIZE);
               casella.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.2))));
               casella.setOnMouseClicked((evt ->{
                   
                   Stage stage = new Stage();
                   Text text;
                   if( casella.getChildren().isEmpty() )
                       text = new Text("Libero");
                   else
                       text = new Text("Occupato");
                   text.setFont(Font.font(45));
                   stage.setScene(new Scene(new StackPane(text)));
                   stage.sizeToScene();
                   stage.show();
                   
               }));
               this.caselleTabella.add(casella);
           }
   }
   
   private void initCaselleRiga(){
       
       this.caselleRiga.clear();
       for( int i  = 0; i < N_CASELLE_RIGA; i++ ){
           
           Casella casella = new Casella(0,i);
           casella.setPrefSize(CELLA_SIZE, CELLA_SIZE);
           casella.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.2))));
           this.caselleRiga.add(casella);
           final int I = i;
           casella.setOnMouseClicked((evt) ->{
               
               if(casella.getChildren().isEmpty()){
                   casella.aggiungiCerchio();
               } else{
                   casella.rimuoviCerchio();
                   for( int j = 0; j < N_CASELLE_RIGA; j++ ){
                       
                       Casella c = (Casella) this.getNodeAtIndexes(j, I);
                       c.rimuoviCerchio();
                       
                   }
               }
           });
           
       }
       
   }
   
   private void popolaTabella(){
       
       for( int i  = 0; i < this.caselleTabella.size(); i++ ){
           
           this.tabella.add(caselleTabella.get(i), i%N_CASELLE_RIGA, i/N_CASELLE_RIGA);
           GridPane.setHgrow(caselleTabella.get(i), Priority.ALWAYS);
           GridPane.setVgrow(caselleTabella.get(i), Priority.ALWAYS);
       }
       
   }
   
   private void popolaRiga(){
       
       for( int i = 0; i < this.caselleRiga.size(); i++ ){
           this.riga.add(this.caselleRiga.get(i), i, 0);
           GridPane.setHgrow(caselleRiga.get(i), Priority.ALWAYS);
           GridPane.setVgrow(caselleRiga.get(i), Priority.ALWAYS);
       }
       
   }
   
   private Node getNodeAtIndexes( long r, long c ){
       
       for( Casella i : this.caselleTabella )
           if( i.getR() == r && i.getC() == c )
               return i;
       return null;
       
   }
   
   private int discretizza(double y, double ymin, double ymax, int nmax ){
       
       return (int)((nmax -1)*(1-(y-ymin)/(ymax - ymin)));
       
   }
}
