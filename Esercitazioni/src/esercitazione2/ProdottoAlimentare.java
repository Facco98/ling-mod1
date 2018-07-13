package esercitazione2;

import java.time.LocalDate;
import java.time.ZoneId;

public class ProdottoAlimentare extends Prodotto {

    protected LocalDate expireDate;

    public ProdottoAlimentare(String descrizione, float prezzo, LocalDate expireDate, Boolean... barCode){

        super(descrizione, prezzo, barCode);
        this.expireDate = expireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Override
    public void applicaSconto(){

        LocalDate now = LocalDate.now(ZoneId.of("Europe/Rome"));
        if(this.expireDate.minusDays(10).isBefore(now))
            this.prezzo = this.prezzo * 80/100;

    }

}
