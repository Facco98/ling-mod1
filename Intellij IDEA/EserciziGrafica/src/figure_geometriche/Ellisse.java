package figure_geometriche;

public class Ellisse extends FiguraGeometrica {


    private final double semiasseMinore;
    private final double semiasseMaggiore;

    public Ellisse( double semiasseMaggiore, double semiasseMinore ){

        super("Ellisse[ semiasseMaggiore: " + semiasseMaggiore+ ", semiasseMinore: " + semiasseMinore + " ]");
        this.semiasseMaggiore = semiasseMaggiore;
        this.semiasseMinore = semiasseMinore;

    }

    protected Ellisse( String descrizione, double semiasseMaggiore, double semiasseMinore ){

        super(descrizione);
        this.semiasseMinore = semiasseMinore;
        this.semiasseMaggiore = semiasseMaggiore;

    }

    @Override
    public double area() {
        return Math.PI * this.semiasseMinore * this.semiasseMaggiore;
    }

    @Override
    public double perimetro() {
        return Math.PI * 2 * Math.sqrt((this.semiasseMaggiore * this.semiasseMaggiore + this.semiasseMinore * this.semiasseMinore) / 2);
    }
}
