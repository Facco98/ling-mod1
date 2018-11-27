package ordini.logica;

public abstract class Persona {

    protected String nome;
    protected String cognome;
    protected String indirizzo;

    public Persona( String nome, String cognome, String indirizzo ){

        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;

    }

    public String getNome(){

        return this.nome;

    }

    public String getCognome() {

        return cognome;

    }

    public String getIndirizzo() {

        return indirizzo;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString(){

        return this.nome + " " + this.cognome;

    }
}
