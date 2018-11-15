package figure_geometriche;

public class Triangolo extends FiguraConLati {

    private final double altezza;

    public Triangolo(double base, double lato1, double lato2, double altezza) throws InvalidTriangleException {

        super("Triangolo( base: " + base + ", latiDistinti: [" + lato1 + ", "+ lato2+ "]", base, lato1, lato2);
        if (!this.isCorrect()){
            throw new InvalidTriangleException("The longest segment cannot be grater then the sum of the others");
        }
        this.altezza = altezza;

    }

    @Override
    public double area() {
        return this.latiDistinti[0] * this.altezza / 2;
    }

    @Override
    public double perimetro(){

        double somma = 0;
        for( double lato : this.latiDistinti)
            somma += lato;
        return somma;

    }

    private boolean isCorrect(){

        double max = Math.max(this.latiDistinti[0], this.latiDistinti[1]);
        max = Math.max(this.latiDistinti[2], max);
        double[] others;
        if( max == this.latiDistinti[0] )
            others = new double[]{ this.latiDistinti[1], this.latiDistinti[2] };
        else if( max == this.latiDistinti[1] )
            others = new double[]{ this.latiDistinti[0], this.latiDistinti[2] };
        else
            others = new double[]{ this.latiDistinti[1], this.latiDistinti[2] };

        double somma = others[0] + others[1];
        return somma >= max;
    }

    public double getAltezza(){

        return this.altezza;

    }
}
