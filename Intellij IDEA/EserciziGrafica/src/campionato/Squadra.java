package campionato;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import javafx.scene.paint.Paint;

public class Squadra implements Comparable<Squadra>{

    private String nome;
    private Bandiera bandiera;
    private int punteggio;

    public Squadra( String nome, GeneratoreBandiera generatore, Paint... colori ) throws WrongNumberArgsException {

        this.nome = nome;
        this.punteggio = 0;
        this.bandiera = generatore.getBandiera(colori);

    }

    public Squadra( String nome, Bandiera bandiera){

        this.nome = nome;
        this.punteggio = 0;
        this.bandiera = bandiera;

    }

    @Override
    public int compareTo(Squadra o) {
        int res;
        if( this.punteggio != o.punteggio )
            res = o.punteggio - this.punteggio;
        else
            res = this.nome.compareTo(o.nome);
        return res;
    }

    public String getNome() {
        return nome;
    }

    public Bandiera getBandiera() {
        return bandiera;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void addPunti(int punti){

        if( punti >= 0 )
            this.punteggio += punti;

    }
}
