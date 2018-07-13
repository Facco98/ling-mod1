package esercitazioneCarte.logica;

public enum Valore {

    UNO (1),
    DUE (2),
    TRE (3),
    QUATTRO (4),
    CINQUE (5);

    private int value;

    Valore(int value){

        this.value = value;

    }

    public String toString(){

        return String.valueOf(this.value);

    }

    public boolean equals(Valore v){

        return this.value == v.value;

    }

}
