package ordini.logica;

import java.util.Collection;
import java.util.HashMap;

public class Ordine extends HashMap<Prodotto, Integer> {

    private Cliente cliente;

    public Ordine(Cliente cliente){
        super();
        this.cliente = cliente;

    }

    public void aggiungiProdotto(Prodotto p, int quantita){

        if( p.getNumeroPezziDisponibili() >= quantita ) {
            Integer res = this.get(p);
            if( res == null ){
                this.put(p, quantita);
            } else{

                this.put(p, res + quantita);

            }
            p.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili() - quantita);
        }

    }

    public Collection<Prodotto> getProdotti(){

        return this.keySet();

    }

    public double calcolaPrezzo(){

        double tot = 0;
        for( Prodotto p : this.keySet() ){

            int quantita = this.get(p);
            tot += p.getPrezzo() * p.getPrezzo();

        }
        return tot;

    }

    public Cliente getCliente() {
        return cliente;
    }
}
