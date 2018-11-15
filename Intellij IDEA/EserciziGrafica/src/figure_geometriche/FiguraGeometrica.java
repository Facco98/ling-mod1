package figure_geometriche;

public abstract class FiguraGeometrica {

    private String descrizione;

    protected FiguraGeometrica( String descrizione ){

        this.descrizione = descrizione;

    }

    public String toString(){

        return this.descrizione;

    }

    public abstract double area();

    public abstract double perimetro();

    public static double sommaAree(FiguraGeometrica... figure){

        double somma = 0;
        for( FiguraGeometrica figura : figure )
            somma += figura.area();
        return somma;

    }

    public static void main(String[] args) throws InvalidTriangleException {

        Cerchio c = new Cerchio(20);
        Quadrato q = new Quadrato(10);
        Rettangolo r = new Rettangolo(30, 10);
        Ellisse e = new Ellisse( 10, 19);
        Triangolo t = new Triangolo(20, 30, 40, 10);

        System.out.println(FiguraGeometrica.sommaAree(c,q,r,e,t));

    }

}
