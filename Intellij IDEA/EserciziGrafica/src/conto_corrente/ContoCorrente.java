package conto_corrente;

public class ContoCorrente implements Comparable<ContoCorrente> {

    private static int id = 0;

    private String idConto;
    protected int saldo;
    private String nomeProprietario;
    private String cognomeProprietario;

    public ContoCorrente( int saldoIniziale, String nomeProprietario, String cognomeProprietario ){

        this.saldo = saldoIniziale;
        this.nomeProprietario = nomeProprietario;
        this.cognomeProprietario = cognomeProprietario;

    }

    @Override
    public int compareTo(ContoCorrente o) {
        return o.saldo-this.saldo;
    }

    private static void incrementID(){

        ContoCorrente.id ++;

    }

    public void prelievo( int cifra ){

        this.saldo -= cifra;

    }

    public void deposito( int cifra ){

        this.saldo += cifra;

    }

    public String getNomeProprietario(){

        return this.nomeProprietario;

    }

    public String getCognomeProprietario(){

        return this.cognomeProprietario;

    }

    public String toString(){

        return "[Saldo: " +this.saldo + ", proprietario: " + this.nomeProprietario +" " + this.cognomeProprietario + " ]";

    }


}
