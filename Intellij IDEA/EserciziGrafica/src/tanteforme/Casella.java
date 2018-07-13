package tanteforme;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Stack;

public class Casella extends VBox {

    public static final String X = "X";
    public static final String C = "C";
    public static final String VUOTA = null;

    private StackPane pane;
    private HBox controlli;
    private String value;

    public Casella(Node... controlli){

        this.pane = new StackPane();
        this.pane.setPrefSize(400,400);
        this.controlli = new HBox(controlli);
        this.getChildren().addAll(pane, this.controlli);
        this.controlli.setSpacing(20);
        this.controlli.setAlignment(Pos.CENTER);
        this.setFillWidth(true);
        this.resetBorder();

    }

    public void addToPane(Node node){

        this.pane.getChildren().clear();
        this.pane.getChildren().add(node);

    }

    public void pulisciPane(){

        this.pane.getChildren().clear();
        this.setValue(VUOTA);

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){

        this.value = value;

    }

    public StackPane getPane(){

        return this.pane;

    }

    public String toString(){

        return "Casella[ " + (this.value == null ? "VUOTA" : this.value ) + " ]";

    }

    public void resetBorder(){

        this.pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

    }

    public void colorBorder(){

        this.pane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

    }
}
