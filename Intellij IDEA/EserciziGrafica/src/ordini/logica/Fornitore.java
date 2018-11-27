package ordini.logica;

public class Fornitore extends Persona {

    private String partitaIva;

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public Fornitore(String nome, String cognome, String indirizzo, String partitaIva) {

        super(nome, cognome, indirizzo);
        this.partitaIva = partitaIva;

    }
}
