package esameterreno;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public abstract class TerrenoOccupabile extends Terreno {

    private Collection<String> entitaAmmesse;
    private int numeroEntitaMassime;
    private ArrayList<Entita> entita;


    public TerrenoOccupabile(TipoTerreno tipoTerreno, Collection<Class> entitaAmmesse, int numeroEntitaAmmesse) {
        super(tipoTerreno);
        this.numeroEntitaMassime = numeroEntitaAmmesse;
        this.entitaAmmesse = new ArrayList<>();
        for( Class i : entitaAmmesse )
            this.entitaAmmesse.add(i.toString());
        this.entita = new ArrayList<Entita>();
    }

    public boolean isAmmessa(String c){

        return this.entitaAmmesse.contains(c);


    }

    public boolean aggiungiEntita(Entita entita){

        boolean res = false;
        System.out.println(this.isAmmessa(entita.getClass().toString()));
        if( this.isAmmessa(entita.getClass().toString()) && this.entita.size() < this.numeroEntitaMassime ) {
            res = this.entita.add(entita);
            Collection<Shape> shapes = new ArrayList<>();
            for( Entita i : this.entita )
                shapes.add(i.getShape());
            Shape[] a = new Shape[shapes.size()];
            shapes.toArray(a);
            System.out.println(a);
            super.setGraphic(new Group(a));

        }
        return res;

    }

    public boolean rimuoviEntita( Entita e ){

        boolean res = this.entita.remove(e);
        return res;
    }

}
