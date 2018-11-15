package conto_corrente;

public class ContoDebito extends ContoCorrente {

    public ContoDebito(int saldoIniziale, String nomeProprietario, String cognomeProprietario) {
        super(saldoIniziale, nomeProprietario, cognomeProprietario);
    }

    public void riconosciInteresse( double interesse ){

        if( interesse >= 0 && interesse <= 1 )
            this.saldo += this.saldo * interesse;

    }
}
