package campionato;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Girone {

    private ArrayList<Squadra> squadre;
    private ArrayList<Giornata> giornate;
    private ArrayList<Partita> partite;
    private int giornateTotali;

    private static ArrayList<Partita> quarti = null;
    private static ArrayList<Partita> semifinali = null;

    public Girone(Squadra[] squadre, Giornata[] giornate){

        this.squadre = new ArrayList<>();
        Collections.addAll(this.squadre, squadre);
        this.giornate = new ArrayList<>();
        Collections.addAll(this.giornate, giornate);
        this.partite = new ArrayList<>();
        for( Giornata giornata : this.giornate){
            for( int[] indiciPartita : giornata.getIndiciPartite() ){

                Partita partita = new Partita(this.squadre.get(indiciPartita[0]), this.squadre.get(indiciPartita[1]));
                this.partite.add(partita);

            }
        }
        this.giornateTotali = giornate.length;

    }

    public void disputaGiornataSuccessiva(){

        if( this.giornate.size() > 0 ) {
            Giornata giornata = this.giornate.get(0);
            int[][] indici = giornata.getIndiciPartite();
            for( int i = 2*(this.giornateTotali-this.giornate.size()); i < 2*(this.giornateTotali-this.giornate.size())+2; i++ ){

                this.partite.get(i).disputa();

            }
            this.giornate.remove(0);
        }

    }

    public Partita[] getGiornataSuccessiva(){

        Partita[] res = null;
        if( this.giornate.size() > 0 ){

            Giornata giornataSuccessiva = this.giornate.get(0);
            res = new Partita[giornataSuccessiva.getIndiciPartite().length];
            for( int i = 0; i < res.length; i++ )
                res[i] = new Partita(this.squadre.get(giornataSuccessiva.getIndiciPartite()[i][0]), this.squadre.get(giornataSuccessiva.getIndiciPartite()[i][0]));



        }
        return res;

    }


    public ArrayList<Squadra> getClassifica(){

        ArrayList<Squadra> classifica = new ArrayList<>(this.squadre);
        Collections.sort(classifica);
        return classifica;

    }

    public ArrayList<Partita> getPartite(){

        return this.partite;

    }

    public static ArrayList<Partita> getQuarti(Collection<Girone> gironi){

        if(quarti == null) {
            quarti = new ArrayList<>();
            ArrayList<ArrayList<Squadra>> classifiche = new ArrayList<>();
            for (Girone g : gironi)
                classifiche.add(g.getClassifica());
            quarti.add(new Partita(classifiche.get(0).get(0), classifiche.get(1).get(1)));
            quarti.add(new Partita(classifiche.get(0).get(1), classifiche.get(1).get(0)));
            quarti.add(new Partita(classifiche.get(2).get(0), classifiche.get(3).get(1)));
            quarti.add(new Partita(classifiche.get(2).get(1), classifiche.get(3).get(0)));
        }
        return quarti;

    }

    public static ArrayList<Partita> getSemifinali(){

        if(semifinali == null) {
            semifinali = new ArrayList<>();
            for (int i = 0; i < quarti.size() / 2 +1; i += 2) {

                semifinali.add(new Partita(quarti.get(i).getVincitore()[0], quarti.get(i + 1).getVincitore()[0]));

            }
        }
        return semifinali;

    }

    public static Partita generaFinale(){

        return new Partita(semifinali.get(0).getVincitore()[0], semifinali.get(1).getVincitore()[0]);

    }

}
