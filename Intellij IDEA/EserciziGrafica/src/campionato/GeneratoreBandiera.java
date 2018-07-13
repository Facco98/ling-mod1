package campionato;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import javafx.scene.paint.Paint;

public interface GeneratoreBandiera {

    /**
     *
     * @param colori I colori della bandiera
     * @return La bandiera desiderata
     * @throws WrongNumberArgsException Nel caso in cui non siano specificati colori
     */
    public Bandiera getBandiera(Paint... colori) throws WrongNumberArgsException;

}
