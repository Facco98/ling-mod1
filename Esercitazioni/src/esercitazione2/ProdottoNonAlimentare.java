package esercitazione2;

public class ProdottoNonAlimentare extends Prodotto{

    protected Material material;

    public ProdottoNonAlimentare(String description, float prezzo, Material material, Boolean... barCode){

        super(description, prezzo, barCode);
        this.material = material;

    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public void applicaSconto(){

        if( this.material.equals(Material.plastic) || this.material.equals(Material.paper) || this.material.equals(Material.glass) )
            this.prezzo = this.prezzo * 90/100;

    }
}
