package bottonecerchio;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CircleEventHandler implements EventHandler<MouseEvent> {

    private CircleManager circleManager;

    public CircleEventHandler( CircleManager circleManager ){

        this.circleManager = circleManager;

    }

    @Override
    public void handle(MouseEvent event) {

        if( this.circleManager.isHorizontal() ) {
            this.circleManager.moveCircleLeft();
        } else{
            this.circleManager.moveCircleUp();
        }
        this.circleManager.HPDown();

    }


}
