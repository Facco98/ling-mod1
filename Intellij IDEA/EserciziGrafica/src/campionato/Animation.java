package campionato;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation {

    public static SequentialTransition createAnimationStage( Circle ball ){


        Shape path1= new QuadCurve(ball.getCenterX(), ball.getCenterY(), ball.getCenterX()+80, ball.getCenterY()-200, ball.getCenterX()+160, ball.getCenterY() );
        PathTransition animation1 = new PathTransition(Duration.seconds(0.5),path1);

        ball.setCenterX(ball.getCenterX()+160);

        Shape path2 = new QuadCurve(ball.getCenterX(), ball.getCenterY(), ball.getCenterX()+40, ball.getCenterY()-130, ball.getCenterX()+80, ball.getCenterY() );
        PathTransition animation2 = new PathTransition(Duration.seconds(0.5),path2);

        ball.setCenterX(ball.getCenterX()+80);

        Shape path3 = new QuadCurve(ball.getCenterX(), ball.getCenterY(), ball.getCenterX()+20, ball.getCenterY()-60, ball.getCenterX()+40, ball.getCenterY() );
        PathTransition animation3 = new PathTransition(Duration.seconds(0.5),path3);

        ball.setCenterX(ball.getCenterX()+40);

        SequentialTransition squentialTransition = new SequentialTransition(ball, animation1, animation2, animation3);

        return squentialTransition;

    }

}
