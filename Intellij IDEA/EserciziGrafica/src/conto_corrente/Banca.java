package conto_corrente;


public class Banca extends SortedList<ContoCorrente> {

    public Banca(){

        super();

    }


    public String toString(){

        return super.toString();

    }

    public static void main( String[] args ){

        ContoCorrente conto1 = new ContoCredito(20, "A", "B");
        conto1.deposito(200);

        ContoDebito conto2 = new ContoDebito(300, "C", "D");
        conto2.riconosciInteresse(0.5);

        ContoCredito conto3 = new ContoCredito(200, "E", "F");
        conto2.deposito(3000);

        Banca banca = new Banca();
        banca.add(conto1);
        banca.add(conto2);
        banca.add(conto3);

        System.out.println(banca);

    }

}
