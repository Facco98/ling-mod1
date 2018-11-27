package ordini.grafica;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ordini.logica.Cliente;
import ordini.logica.Fornitore;
import ordini.logica.Ordine;
import ordini.logica.Prodotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GUI extends Application {


    private ArrayList<Cliente> clienti = new ArrayList<>();
    private ArrayList<Fornitore> fornitori = new ArrayList<>();
    private HashMap<String, Prodotto> prodotti = new HashMap<>();
    private HashMap<String, Ordine> ordini = new HashMap<>();

    private Stage stageAggiungiPersone, stageAggiungiProdotto, stageOrdina;

    public HashMap<String, Prodotto> getProdotti() {

        return prodotti;
    }


    public ArrayList<Cliente> getClienti() {
        return clienti;
    }

    public ArrayList<Fornitore> getFornitori() {
        return fornitori;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI();
        Button btnAggiungiPersone = new Button("Gestisci persone");
        Button btnAggiungiOrdine = new Button("Aggiungi Ordine");
        Button btnAggiungiProdotto = new Button("Aggiungi prodotto");
        Button btnCalcolaOrdine = new Button("Totale ordine");

        btnAggiungiOrdine.setOnAction(e ->{

            this.stageOrdina.show();

        });

        btnAggiungiPersone.setOnAction(e ->{

            this.stageAggiungiPersone.show();

        });

        btnAggiungiProdotto.setOnAction(e ->{

            this.stageAggiungiProdotto.show();

        });

        btnCalcolaOrdine.setOnAction(e ->{

            for( Map.Entry<String, Ordine> i : this.ordini.entrySet() ){

                System.out.println(i.getKey());
                System.out.println(i.getValue().calcolaPrezzo());
                System.out.println(" ------- ");

            }

            this.ordini = new HashMap<>();

        });

        VBox vbox =new VBox(btnAggiungiPersone, btnAggiungiProdotto, btnAggiungiOrdine, btnCalcolaOrdine);
        Scene scene = new Scene(vbox);

        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    private void initGUI(){

        this.stageAggiungiPersone = new StageAggiungiPersone(this);
        this.stageAggiungiProdotto = new StageAggiungiProdotto(this);
        this.stageOrdina = new StageOrdine(this);

    }

    public void aggiungiOrdini(HashMap<String, Ordine> ordini){

        for( Ordine i : ordini.values() ){

            String k = i.getCliente().getCodiceFiscale();
            Ordine ord = this.ordini.get(k);
            if( ord == null ){

                ord = new Ordine(i.getCliente());


            }

            for( Prodotto p : i.getProdotti() ){

                Integer q = ord.get(p);
                if( q == null )
                    q = 0;
                q += i.get(p);

                ord.put(p, q);

            }

            this.ordini.put(k, ord);

        }

    }
}
