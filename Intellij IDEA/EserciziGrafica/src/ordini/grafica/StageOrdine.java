package ordini.grafica;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ordini.logica.Cliente;
import ordini.logica.Ordine;
import ordini.logica.Prodotto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StageOrdine extends Stage {

    private GUI gui;
    private HashMap<String, Ordine> ordini;
    private Form form;

    public StageOrdine( GUI gui ){

        this.gui = gui;
        this.ordini = new HashMap<>();
        ArrayList<String> campi = new ArrayList<>();
        Collections.addAll(campi, "Codice Fiscale Cliente", "Codice Prodotto", "Quantita");

        ArrayList<ButtonWithListener> buttons = new ArrayList<>();
        ButtonWithListener btnChiudi = new ButtonWithListener("Chiudi", fields ->{

            this.gui.aggiungiOrdini(this.ordini);
            this.ordini = new HashMap<>();
            this.close();

        });

        ButtonWithListener btnAggiungi = new ButtonWithListener("Aggiungi all'ordine", fields ->{

            String codiceFiscale = fields[0].getText().trim();
            String codiceProdotto = fields[1].getText().trim();
            int quantita = Integer.parseInt(fields[2].getText().trim());


            Prodotto prodotto = null;
            for( Map.Entry<String, Prodotto> entry : gui.getProdotti().entrySet() ){

                Prodotto corrente = entry.getValue();
                if( corrente.getCodice().equals(codiceProdotto)) {

                    prodotto = corrente;

                }

            }

            Cliente cliente = null;
            for( int i = 0; i < gui.getClienti().size() && cliente == null; i++ ){

                Cliente corrente = gui.getClienti().get(i);
                if( corrente.getCodiceFiscale().equals(codiceFiscale)) {

                    cliente = corrente;

                }

            }

            if( cliente == null || prodotto == null ){

                new Alert(Alert.AlertType.ERROR, "Il cliente o il prodotto non esistono");

            } else{

                Ordine ordine = this.ordini.get(codiceFiscale);
                if( ordine == null ){

                    ordine = new Ordine(cliente);
                    this.ordini.put(cliente.getCodiceFiscale(), ordine);
                }
                ordine.aggiungiProdotto(prodotto, quantita);

            }


        });

        buttons.add(btnChiudi);
        buttons.add(btnAggiungi);
        this.form= new Form(campi, buttons);
        Scene scene = new Scene(this.form);
        this.setScene(scene);



    }



}
