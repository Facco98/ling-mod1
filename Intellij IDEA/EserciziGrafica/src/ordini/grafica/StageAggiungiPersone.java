package ordini.grafica;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ordini.logica.Cliente;
import ordini.logica.Fornitore;

import java.util.ArrayList;
import java.util.Collections;

public class StageAggiungiPersone extends Stage {

    private Button btnAggiuntaTerminata;
    private Form formCliente, formFornitore;
    private GUI gui;

    public StageAggiungiPersone(GUI gui){


        this.gui = gui;

        ArrayList<String> campiCliente = new ArrayList<>();
        Collections.addAll(campiCliente, "Nome Cliente", "Cognome Cliente", "Indirizzo Cliente", "Codice Fiscale Cliente");
        ArrayList<ButtonWithListener> bottoniFormCliente = new ArrayList<>();
        ButtonWithListener btnAggiungiCliente = new ButtonWithListener("Aggiungi Cliente", fields ->{

            String nome = fields[0].getText();
            String cognome = fields[1].getText();
            String indirizzo = fields[2].getText();
            String codiceFiscale = fields[3].getText();

            gui.getClienti().add(new Cliente(nome, cognome, indirizzo, codiceFiscale));
            System.out.println(gui.getClienti());

        });
        bottoniFormCliente.add(btnAggiungiCliente);
        this.formCliente = new Form(campiCliente, bottoniFormCliente);

        ArrayList<String> campiFornitore = new ArrayList<>();
        Collections.addAll(campiFornitore, "Nome Fornitore", "Cognome Fornitore", "Indirizzo Fornitore", "Codice Fiscale Fornitore");
        ArrayList<ButtonWithListener> bottoniFormFornitore = new ArrayList<>();
        ButtonWithListener btnAggiungiFornitore = new ButtonWithListener("Aggiungi Fornitore", fields ->{

            String nome = fields[0].getText();
            String cognome = fields[1].getText();
            String indirizzo = fields[2].getText();
            String partitaIva = fields[3].getText();

            gui.getFornitori().add(new Fornitore(nome, cognome, indirizzo, partitaIva));
            System.out.println(gui.getFornitori());

        });
        bottoniFormFornitore.add(btnAggiungiFornitore);
        this.formFornitore = new Form(campiFornitore, bottoniFormFornitore);



        this.btnAggiuntaTerminata = new Button("Aggiunta terminata");
        this.btnAggiuntaTerminata.setOnAction( e ->{


            this.close();

        });

        HBox forms = new HBox(this.formCliente, this.formFornitore);
        VBox secondaryRoot = new VBox(forms, btnAggiuntaTerminata);
        Scene scene = new Scene(secondaryRoot);

        this.setScene(scene);
        this.sizeToScene();

    }
}
