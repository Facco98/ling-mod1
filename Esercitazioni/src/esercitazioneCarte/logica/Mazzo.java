package esercitazioneCarte.logica;

import esercitazioneCarte.exception.DeckIsEmptyException;

import java.util.ArrayList;
import java.util.Collections;

public class Mazzo extends ArrayList<CartaDaGioco> {

    public static final int numeroMazziSemplici = 2;

    public Mazzo(){

        super();
        this.create();

    }

    public void mescola(){

        Collections.shuffle(this);

    }

    public CartaDaGioco pesca() throws DeckIsEmptyException {

        if( this.size() == 0 )
            throw new DeckIsEmptyException("Il mazzo è vuoto");
        CartaDaGioco pescata = this.get(0);
        this.remove(0);
        return pescata;

    }

    private void create(){

        MazzoSemplice mazzoSemplice = new MazzoSemplice();
        for( int i = 0; i < Mazzo.numeroMazziSemplici; i++ )
            this.addAll(mazzoSemplice);

    }

    @Override
    public String toString(){

        return this.size() + " elementi: " + super.toString();

    }

    public int hashCode(){

        return 0;

    }


}


class MazzoMain{

    public static void main(String[] args){

        new MazzoMain();

    }

    public MazzoMain(){

        Mazzo mazzo = new Mazzo();
        System.out.println(mazzo);
        Collections.shuffle(mazzo);
        System.out.println(mazzo);

    }

}