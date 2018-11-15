package figure_geometriche;

public class Rettangolo extends FiguraConLati {


    public Rettangolo(double base, double altezza){

        super("Rettangolo[ base: "+ base + ", altezza: " + altezza + " ]", base, altezza);

    }

    protected Rettangolo(String descrizione, double base, double altezza){

        super(descrizione, base, altezza);

    }


    @Override
    public double area() {
        return this.latiDistinti[0] * this.latiDistinti[1];
    }

    @Override
    public double perimetro(){

        return ( this.latiDistinti[0] + this.latiDistinti[1] ) * 2;

    }
}
