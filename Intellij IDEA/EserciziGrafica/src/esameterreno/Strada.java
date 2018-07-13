package esameterreno;

import java.util.Arrays;
import java.util.Collection;

public class Strada extends TerrenoOccupabile {


    public Strada() {
        super(TipoTerreno.STRADA, Arrays.asList(new Class[]{Automobile.class}), 1);
    }


}
