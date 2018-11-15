import java.util.*;

public class Scuola{

  private String nome;
  private String indirizzo;
  private Provveditorato provveditorato;
  private ArrayList<Classe> classi;
  private ArrayList<Insegnante> insegnanti;

  public Scuola( String nome, String indirizzo, Provveditorato provveditorato){

    this.nome = nome;
    this.indirizzo = indirizzo;
    this.provveditorato = provveditorato;
    this.insegnanti = new ArrayList<>();
    this.classi = new ArrayList<>();

  }
  public String getNome(){

    return this.nome;

  }

  public String indirizzo(){

    return this.indirizzo;

  }

  public Provveditorato getProvveditorato(){

    return this.provveditorato;

  }

  public void aggiungiInsegnante( Insegnante insegnante ){

    this.insegnanti.add(insegnante);

  }

  public Insegnante[] getInsegnanti(){

    Insegnante[] insegnanti = new Insegnante[this.insegnanti.size()];
    this.insegnanti.toArray(insegnanti);
    return insegnanti;

  }

  public void aggiungiClasse( Classe classe ){

    this.classi.add(classe);

  }

  public Classe[] getClassi(){

    Classe[] classi = new Classe[this.classi.size()];
    this.classi.toArray(classi);
    return classi;

  }


}
