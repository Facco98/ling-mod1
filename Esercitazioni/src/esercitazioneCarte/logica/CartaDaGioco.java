package esercitazioneCarte.logica;

public class CartaDaGioco {

    private Seme seme;
    private Valore valore;

    public CartaDaGioco( Seme seme, Valore valore ){

        this.seme = seme;
        this.valore = valore;

    }

    public Seme getSeme() {

        return seme;
    }

    public void setSeme(Seme seme) {
        this.seme = seme;
    }

    public Valore getValore() {
        return valore;
    }

    public void setValore(Valore valore) {
        this.valore = valore;
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if(o instanceof CartaDaGioco){
            CartaDaGioco tmp = (CartaDaGioco) o;
            if( this.hashCode() == tmp.hashCode() )
                res = this.seme.equals(tmp.seme) && this.valore.equals(tmp.valore);
        }
        return res;
    }

    @Override
    public int hashCode() {
        int result = seme.hashCode();
        result = 31 * result + valore.hashCode();
        return result;
    }

    @Override
    public String toString(){

        return this.valore.toString() + " " + this.seme.toString();

    }


}

class CartaMain{

    public static void main(String[] args){

        new CartaMain();

    }

    public CartaMain(){

        CartaDaGioco carta = new CartaDaGioco(Seme.Q, Valore.DUE);
        System.out.println(carta.getSeme());
        System.out.println(carta.getValore());
        System.out.println(carta);

    }

}
