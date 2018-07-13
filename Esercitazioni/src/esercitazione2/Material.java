package esercitazione2;

public class Material {

    public static Material paper = new Material("Paper");
    public static Material glass = new Material("Glass");
    public static Material plastic = new Material("Plastic");
    public static Material stone = new Material("Stone");
    public static Material wood = new Material("Wood");

    private String mat;

    private Material(String material){

        this.mat = material;

    }

    public String getMat() {
        return mat;
    }

    public boolean equals(Material material){

        return this.mat.equals(material.mat);

    }
}
