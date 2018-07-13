package esercitazioneCarte.logica;

public enum Seme {

    C ( "C" ),
    Q ( "Q" );

    private String value;

    Seme(String value){

        this.value = value;

    }

    public boolean equals(Seme s){

        return this.value.equals(s.value);

    }

    public String toString(){

        return this.value;

    }

}
