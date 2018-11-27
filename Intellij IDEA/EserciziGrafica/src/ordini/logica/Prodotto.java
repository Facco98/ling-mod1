package ordini.logica;

import java.io.Serializable;

public class Prodotto implements Serializable {

    private String codice;
    private Fornitore fornitore;
    private String descrizione;
    private double prezzo;
    private int numeroPezziDisponibili;


    public Prodotto(String codice, Fornitore fornitore, String descrizione, double prezzo, int numeroPezziDisponibili) {
        this.codice = codice;
        this.fornitore = fornitore;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.numeroPezziDisponibili = numeroPezziDisponibili;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getNumeroPezziDisponibili() {
        return numeroPezziDisponibili;
    }

    public void setNumeroPezziDisponibili(int numeroPezziDisponibili) {
        this.numeroPezziDisponibili = numeroPezziDisponibili;
    }

    @Override
    public String toString(){

        return "" + this.codice + ": " + this.descrizione;

    }
}
