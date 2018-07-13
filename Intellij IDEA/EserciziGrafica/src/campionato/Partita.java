package campionato;

import java.util.Random;

public class Partita {

    private Squadra squadra1, squadra2;
    private int gol1, gol2;
    private boolean disputata;

    public Partita(Squadra squadra1, Squadra squadra2){

        this.disputata = false;
        this.squadra1 = squadra1;
        this.squadra2 = squadra2;
        this.gol1 = this.gol2 = 0;

    }

    public boolean isDisputata(){

        return this.disputata;

    }

    public void disputa(){

        double random = Math.random();
        Random randomGenerator = new Random();
        if( random <= 0.33 ) {
            gol1 = gol2 = new Random().nextInt(3);
            this.squadra1.addPunti(1);
            this.squadra2.addPunti(1);
        }
        else if( random <= 0.66 ){
            gol1 = randomGenerator.nextInt(2) + 1;
            gol2 = randomGenerator.nextInt(gol1);
            this.squadra1.addPunti(3);
        } else{
            gol2 = randomGenerator.nextInt(2) + 1;
            gol1 = randomGenerator.nextInt(gol2);
            this.squadra2.addPunti(3);
        }
        this.disputata = true;

    }

    public Squadra[] getVincitore() {

        Squadra[] res = null;
        if( disputata ) {
            if (gol1 == gol2)
                res = new Squadra[]{this.squadra1, this.squadra2};
            else {
                if (gol1 > gol2)
                    res = new Squadra[]{this.squadra1};
                else
                    res = new Squadra[]{this.squadra2};

            }
        }
        return res;
    }

    public Squadra getSquadra1() {
        return squadra1;
    }

    public Squadra getSquadra2() {
        return squadra2;
    }

    public int getGol1() {
        return gol1;
    }

    public int getGol2() {
        return gol2;
    }

    public void disputaNoPareggio(){

        double random = Math.random();
        Random randomGenerator = new Random();
        if( random <= 0.5 ){
            gol1 = randomGenerator.nextInt(2) + 1;
            gol2 = randomGenerator.nextInt(gol1);
            this.squadra1.addPunti(3);
        } else{
            gol2 = randomGenerator.nextInt(2) + 1;
            gol1 = randomGenerator.nextInt(gol2);
            this.squadra2.addPunti(3);
        }
        this.disputata = true;

    }
}
