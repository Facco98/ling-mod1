import java.util.*;

public class Classe{

  private Scuola scuola;
  private String nome;
  private ArrayList<Insegnante> insegnanti;
  private int numeroDiStudenti;

  public Classe( Scuola scuola, String nome, int numeroDiStudenti, Insegnante... insegnanti ) throws NumeroDiInsegnatiErratoException{

    if( insegnanti.length != 3 )
      throw new NumeroDiInsegnatiErratoException("Expected 3 teachers, found "+insegnanti.length);
    this.scuola = scuola;
    this.nome = nome;
    this.numeroDiStudenti = numeroDiStudenti;
    if( this.numeroDiStudenti < 1 )
      this.numeroDiStudenti = 1;
    this.insegnanti = new ArrayList<>();
    Collections.addAll(this.insegnanti, insegnanti);
    Collections.sort(this.insegnanti);
    this.scuola.aggiungiClasse(this);

  }

  public Scuola getScuola(){

    return this.scuola;

  }

  public String getNome(){

    return this.nome;

  }

  public Insegnante[] getInsegnanti(){

    Insegnante[] res = new Insegnante[3];
    this.insegnanti.toArray(res);
    return res;

  }

  public void aggiungiStudente(){

    this.numeroDiStudenti ++;

  }

  public void rimuoviStudente(){

    this.numeroDiStudenti --;
    if( this.numeroDiStudenti < 1 )
      this.numeroDiStudenti = 1;

  }


}
