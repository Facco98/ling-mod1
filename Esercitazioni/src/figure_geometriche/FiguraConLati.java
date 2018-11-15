package figure_geometriche;

public abstract class FiguraConLati extends FiguraGeometrica {

    protected final double[] latiDistinti;

    protected FiguraConLati(String descrizione, double... latiDistinti){

        super(descrizione);
        this.latiDistinti = latiDistinti;

    }

    public abstract double area();

    public abstract double perimetro();


}
