package esercitazioneCarte.logica;

import java.util.HashSet;

public class MazzoSemplice extends HashSet<CartaDaGioco> {

    public MazzoSemplice(){

        super();
        this.create();
    }

    private void create(){

        for( Seme s : Seme.values() )
            for( Valore v : Valore.values() )
                this.add(new CartaDaGioco(s, v));
    }


    @Override
    public String toString(){

        return this.size() + " elementi: " + super.toString();

    }


    public int hashCode(){

        return 0;

    }
}


class MazzoSempliceMain{

    public static void main(String[] args){

        new MazzoSempliceMain();

    }

    public MazzoSempliceMain(){

        MazzoSemplice mazzoSemplice = new MazzoSemplice();
        System.out.println(mazzoSemplice);

    }

}