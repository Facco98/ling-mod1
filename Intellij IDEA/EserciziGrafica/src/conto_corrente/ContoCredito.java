package conto_corrente;

public class ContoCredito extends ContoCorrente {

    private static final int soglia = 100;

    private int commissione;
    private int numeroOperazioni;
    private int sogliaConto;

    public ContoCredito(int saldoIniziale, String nomeProprietario, String cognomeProprietario) {
        super(saldoIniziale, nomeProprietario, cognomeProprietario);
        this.commissione = 0;
        this.numeroOperazioni = 0;
        this.sogliaConto = soglia;
    }

    public void updateCommissione( int commissione ){

        if( commissione >= 0 )
            this.commissione = commissione;

    }

    public void updateSoglia( int soglia ){

        if( soglia >= 1 )
            this.sogliaConto = soglia;

    }

    @Override
    public void deposito(int cifra) {
        super.deposito(cifra);
        this.controllaCommissione();

    }

    @Override
    public void prelievo(int cifra) {
        super.prelievo(cifra);
        this.controllaCommissione();
    }

    public void controllaCommissione(){

        this.numeroOperazioni ++;
        if( this.numeroOperazioni > this.sogliaConto )
            this.saldo -= this.commissione;

    }

    public void reset(){

        this.numeroOperazioni = 0;

    }
}
