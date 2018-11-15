import java.util.*;

public class Insegnante implements Comparable<Insegnante>{

  private String nome;
  private String cognome;
  private int annoVincita;
  private Scuola scuola;

  public Insegnante( String nome, String cognome, int annoVincita, Scuola scuola ){

    this.nome = nome;
    this.cognome = cognome;
    this.annoVincita = annoVincita;
    this.scuola = scuola;
    this.scuola.aggiungiInsegnante(this);

  }

  public String getNome(){

    return this.nome;

  }

  public String getCognome(){

    return this.cognome;

  }

  public int getAnnoVincita(){

    return this.annoVincita;

  }

  public Scuola scuola(){

    return this.scuola;

  }

  public int compareTo(Insegnante insegnante){

    int res = this.cognome.compareTo(insegnante.cognome);
    if( res == 0 )
      res = this.nome.compareTo(insegnante.nome);
    if( res == 0 )
      res = this.annoVincita - insegnante.annoVincita;
    return res;

  }

}
