package esameterreno;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

public class Terreno extends Button {

    private TipoTerreno tipoTerreno;
    private boolean isOccupabile;

    public Terreno(TipoTerreno tipoTerreno){

        super();
        this.setTipoTerreno(tipoTerreno);
        super.setPrefSize(50,50);

    }

    public void setTipoTerreno(TipoTerreno tipoTerreno) {

        this.tipoTerreno = tipoTerreno;
        super.setBackground(new Background(new BackgroundFill(tipoTerreno.getBackground(), null, null)));

    }


}
