package esercitazioneCarte.logica;

import esercitazioneCarte.exception.DeckIsEmptyException;
import esercitazioneCarte.exception.NotEnoughCardsInDeckException;

import java.util.ArrayList;

public class Mano extends ArrayList<CartaDaGioco> {

    public static final int carteInMano = 5;

    public Mano(){
        super(Mano.carteInMano);
    }

    public Mano(Mazzo mazzo) throws NotEnoughCardsInDeckException {

        super(Mano.carteInMano);
        if( mazzo.size() < Mano.carteInMano )
            throw new NotEnoughCardsInDeckException("Richieste: " + Mano.carteInMano + " carte; "+mazzo.size() + " carte nel mazzo");
        for( int i = 0; i < Mano.carteInMano; i++ ){
            CartaDaGioco carta = mazzo.get(0);
            mazzo.remove(0);
            this.add(carta);
        }


    }

    @Override
    public String toString(){

        return super.toString();

    }



}


class ManoMain{

    public static void main(String[] args){

        new ManoMain();
    }

    public ManoMain(){

        Mazzo mazzo = new Mazzo();
        Mano mano = new Mano();
        mazzo.mescola();

        for( int i = 0; i < Mano.carteInMano; i++ ){

            try {
                mano.add(mazzo.pesca());
            } catch (DeckIsEmptyException e) {
                e.printStackTrace();
            }

        }
        System.out.println(mano);


        mazzo = new Mazzo();
        mazzo.mescola();
        try {
            mano = new Mano(mazzo);
        } catch (NotEnoughCardsInDeckException e) {
            e.printStackTrace();
        }
        System.out.println(mano);

    }

}