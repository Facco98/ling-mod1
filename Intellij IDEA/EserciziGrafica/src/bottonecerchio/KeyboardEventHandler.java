package bottonecerchio;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyboardEventHandler implements EventHandler<KeyEvent> {

    private CircleManager circleManager;

    public KeyboardEventHandler( CircleManager circleManager ){

        this.circleManager = circleManager;

    }

    @Override
    public void handle(KeyEvent event) {

        if( event.getCode().equals(KeyCode.UP) ){

            this.circleManager.moveCircleUp();

        } else if( event.getCode().equals(KeyCode.DOWN) ){

            this.circleManager.moveCircleDown();

        } else if( event.getCode().equals(KeyCode.RIGHT) ){

            this.circleManager.moveCircleRight();

        } else if( event.getCode().equals(KeyCode.LEFT) ){

            this.circleManager.moveCircleLeft();

        }
        if( event.isControlDown() && event.isShiftDown() && event.getCode().equals(KeyCode.A) ){
            this.circleManager.HPZero();
        } else if( event.isControlDown() && event.isShiftDown() && event.getCode().equals(KeyCode.R)){
            this.circleManager.reset();
        } else {
            this.circleManager.circleBlood();
        }
        this.circleManager.circleColor();

    }

}
