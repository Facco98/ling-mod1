package ordini.grafica;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;

public class Form extends VBox {

    private ArrayList<HBox> campi;
    private HBox pulsanti;

    public Form(Collection<String> campi, Collection<ButtonWithListener> pulsanti){

        this.campi = new ArrayList<>();
        for( String i : campi ){

            Text text = new Text(i);
            TextField textField = new TextField();

            this.campi.add(new HBox(text, textField));
        }

        this.pulsanti = new HBox();
        for( ButtonWithListener i : pulsanti ){

            i.setForm(this);
            this.pulsanti.getChildren().add(i);

        }

        this.getChildren().addAll(this.campi);
        this.getChildren().addAll(this.pulsanti);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    }

    public TextField[] getFields(){

        TextField[] campi = new TextField[this.campi.size()];
        for( int i = 0; i < this.campi.size(); i++ ){

            campi[i] = (TextField) this.campi.get(i).getChildren().get(1);

        }
        return campi;

    }

}
