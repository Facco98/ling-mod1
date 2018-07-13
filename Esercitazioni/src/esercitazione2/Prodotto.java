package esercitazione2;

import java.util.ArrayList;
import java.util.Collections;

public class Prodotto {

    protected ArrayList<Boolean> barCode;
    protected String description;
    protected float prezzo;

    public Prodotto(String description, float prezzo, Boolean... barcode){

        this.barCode = new ArrayList<>();
        Collections.addAll(this.barCode, barcode);
        this.prezzo = prezzo;


    }

    public ArrayList<Boolean> getBarCode() {
        return new ArrayList<>(this.barCode);
    }

    public String getDescription() {
        return description;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void applicaSconto(){

        this.prezzo = (this.prezzo * 95/100);

    }




}
