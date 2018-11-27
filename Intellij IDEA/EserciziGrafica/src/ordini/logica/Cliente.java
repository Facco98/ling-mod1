package ordini.logica;

public class Cliente extends Persona {

    private String codiceFiscale;

    public Cliente(String nome, String cognome, String indirizzo, String codiceFiscale) {
        super(nome, cognome, indirizzo);
        this.codiceFiscale = codiceFiscale;
    }


    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
