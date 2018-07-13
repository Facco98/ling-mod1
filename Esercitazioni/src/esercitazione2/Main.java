package esercitazione2;

import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args){

        Prodotto p = new Prodotto("Bicchiere", (float) 1, new Boolean[]{Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE});
        p.applicaSconto();
        System.out.println(p.getPrezzo());
        ProdottoNonAlimentare nonAlimentare = new ProdottoNonAlimentare("Bicchiere di vetro", 1, Material.glass, new Boolean[]{Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE});
        nonAlimentare.applicaSconto();
        System.out.println(nonAlimentare.getPrezzo());
        ProdottoAlimentare alimentare = new ProdottoAlimentare("Prosciutto cotto", 1, LocalDate.of(2018, 3, 20), new Boolean[]{Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE});
        alimentare.applicaSconto();
        System.out.println(alimentare.getPrezzo());


    }

}
