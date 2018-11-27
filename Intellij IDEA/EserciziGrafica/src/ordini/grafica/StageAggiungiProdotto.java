package ordini.grafica;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ordini.logica.Fornitore;
import ordini.logica.Prodotto;

import java.util.ArrayList;
import java.util.Collections;

public class StageAggiungiProdotto extends Stage {

    private GUI gui;
    private Form form;

    public StageAggiungiProdotto(GUI gui){

        this.gui = gui;
        ArrayList<String> campi = new ArrayList<>();
        Collections.addAll(campi, "Codice", "Partita IVA Fornitore", "Descrizione", "Prezzo", "Numero Pezzi");
        ArrayList<ButtonWithListener> buttons = new ArrayList<>();
        ButtonWithListener btn = new ButtonWithListener("Aggiungi", fields ->{

            String codice = fields[0].getText();
            String partitaIvaFornitore = fields[1].getText().trim();
            String desc = fields[2].getText();
            double prezzo = Double.parseDouble(fields[3].getText().trim());
            int numeroPezzi = Integer.parseInt(fields[4].getText().trim());
            Fornitore fornitore = null;
            for( int i  = 0; i < gui.getFornitori().size() && fornitore == null; i++ ){

                Fornitore current = gui.getFornitori().get(i);
                if( current.getPartitaIva().equals(partitaIvaFornitore) ){

                    fornitore = current;

                }


            }

            if(fornitore == null){

                new Alert(Alert.AlertType.ERROR, "Non esiste quel fornitore").showAndWait();

            } else{

                Prodotto p = new Prodotto(codice, fornitore, desc, prezzo, numeroPezzi);
                if( gui.getProdotti().get(p.getCodice()) != null ){

                    int numeroDisponibili = gui.getProdotti().get(p.getCodice()).getNumeroPezziDisponibili() + p.getNumeroPezziDisponibili();
                    p.setNumeroPezziDisponibili(numeroDisponibili);
                    gui.getProdotti().put(p.getCodice(), p);

                } else{

                    gui.getProdotti().put(p.getCodice(), p);

                }
                System.out.println(gui.getProdotti());

            }



        });

        buttons.add(btn);
        this.form = new Form(campi, buttons);

        Scene scene = new Scene(this.form);
        this.setScene(scene);


    }

}
