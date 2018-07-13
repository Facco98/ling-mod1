package esercitazioneCarte.main;

import esercitazioneCarte.exception.DeckIsEmptyException;
import esercitazioneCarte.exception.NotEnoughCardsInDeckException;
import esercitazioneCarte.logica.CartaDaGioco;
import esercitazioneCarte.logica.Mano;
import esercitazioneCarte.logica.Mazzo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gioco {

    public static final int numeroIterazioni = 5;

    public static void main(String[] args){

        new Gioco();

    }

    public boolean playerWon(Mano mano){

        boolean continua = true;
        for( int i = 0; i < mano.size() && continua; i++ )
            if( mano.lastIndexOf(mano.get(i)) != mano.indexOf(mano.get(i))) {
                continua = false;
            }
        return !continua;

    }

    private int menu(BufferedReader reader, Mano mano){

        int val = 0;
        try {
            String scelta;
            do {
                System.out.println("La tua mano: " + mano);
                System.out.println("Scegli quale carta scartare [1:" + mano.size() + "]");
                scelta = reader.readLine().trim();
                val = Integer.parseInt(scelta) -1;
            } while (val -1 >= mano.size() || val < 0 );
        } catch (Exception ex ){
            ex.printStackTrace();
        }
        return val;


    }

    public Gioco(){

        Mazzo mazzo = new Mazzo();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Mano mano = new Mano(mazzo);
            boolean haVinto = this.playerWon(mano);
            for (int i = 0; i < numeroIterazioni + 1 && !haVinto; i++) {

                int indice = this.menu(reader, mano);
                mano.remove(indice);
                CartaDaGioco carta = mazzo.pesca();
                mano.add(carta);
                haVinto = this.playerWon(mano);
            }
            System.out.println("La tua mano: " +mano);
            if( haVinto ) {
                System.out.println("Complimenti hai vinto");
            }
            else{
                System.out.println("Hai perso");
            }

        } catch (NotEnoughCardsInDeckException e) {
            System.out.println("Non ci sono abbastanza carte nel mazzo");
        } catch (DeckIsEmptyException e) {
            System.out.println("Il mazzo è finito");
        }


    }

}


class A{

    static int conta = 0;


    public A(){
        conta ++;

    }


}