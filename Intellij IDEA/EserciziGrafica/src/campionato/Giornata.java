package campionato;

public enum Giornata {
    PRIMA(new int[][]{{0,1}, {2,3}}),
    SECONDA(new int[][]{{0,2}, {1,3}}),
    TERZA(new int[][]{{0,3}, {1,2}});

    private int[][] indiciPartite;
    Giornata(int[][] indiciPartite){

        this.indiciPartite = indiciPartite;

    }

    public int[][] getIndiciPartite(){

        return this.indiciPartite;

    }

}
