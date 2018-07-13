package bottonecerchio;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class MoveButtonEventHandler implements EventHandler<ActionEvent> {

    private CircleManager circleManager;

    public MoveButtonEventHandler( CircleManager circleManager ){

        this.circleManager = circleManager;

    }

    @Override
    public void handle(ActionEvent event) {

        this.circleManager.moveCircleRight();
        this.circleManager.circleColor();
        this.circleManager.circleBlood();

    }
}
