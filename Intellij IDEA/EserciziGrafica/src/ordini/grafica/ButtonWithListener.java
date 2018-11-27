package ordini.grafica;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ButtonWithListener extends Button {

    private Form form;

    public Form getForm() {
        return form;
    }

    public ButtonWithListener(String text, FormListener handler){

        super(text);
        this.setOnAction(handler);
        this.form = null;

    }

    public void setForm( Form form ){

        this.form = form;

    }

}

interface FormListener extends EventHandler<ActionEvent>{

    public default void handle(ActionEvent evt){

        this.compute(((ButtonWithListener) evt.getSource()).getForm().getFields());

    }

    public void compute(TextField... fields);

}