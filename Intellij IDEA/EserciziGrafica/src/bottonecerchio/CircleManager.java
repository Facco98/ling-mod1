package bottonecerchio;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CircleManager {

    public static final Color DEFAULT_COLOR = Color.rgb(0,255,0);
    public static double deltaRadius = 1.0;
    public static final int MAX_LIMIT = 10;


    private Circle circle;
    private int RED;
    private int GREEN;
    private int movement;
    private boolean horizontal;
    private Stage stage;
    private Group root;
    private double initialRadius;

    private int max;
    private int count;


    private ArrayList<Circle> bloods;

    public CircleManager( Circle circle, Stage stage, Group root, int movement ){
        this.circle = circle;
        this.movement = movement;
        this.stage = stage;
        this.RED = 0;
        this.GREEN = 255;
        this.horizontal = true;
        this.root = root;
        this.bloods = new ArrayList<>();
        this.max = 0;
        this.count = 0;
        this.initialRadius = circle.getRadius();
    }

    public void moveCircleLeft(){

        circle.setCenterX(circle.getCenterX() - this.movement);
        if (circle.getCenterX() - circle.getRadius() < 0)
            circle.setCenterX(this.stage.getWidth() - circle.getRadius());

    }

    public void moveCircleRight(){
        circle.setCenterX(circle.getCenterX() + 10);
        if (circle.getCenterX() + circle.getRadius() > this.stage.getWidth())
            circle.setCenterX(circle.getRadius());
    }

    public void moveCircleUp(){
        circle.setCenterY(circle.getCenterY() - 10);
        if( circle.getCenterY()-circle.getRadius() < 0 )
            circle.setCenterY(this.stage.getHeight() - circle.getRadius()*2);
    }

    public void moveCircleDown(){

        this.circle.setCenterY(this.circle.getCenterY() + 10);
        if( this.circle.getCenterY()+this.circle.getRadius() > this.stage.getHeight() )
            this.circle.setCenterY(this.circle.getRadius());

    }

    public void circleColor(){

        this.circle.setFill(Color.rgb(this.RED, this.GREEN, 0));

    }

    public boolean isHorizontal(){

        return this.horizontal;

    }

    public void toggleHorizontal(){

        this.horizontal = !this.horizontal;

    }

    public void HPDown(){

        if( this.RED +5 <= 255 ){

            this.RED += 5;
            this.GREEN -= 5;

        }

    }

    public void circleBlood(){

        if( RED == 255 ){

            if( this.circle.getRadius() < 1 )
                this.reset();

            System.out.println(this.max);
            System.out.println(this.count);
            if( this.count >= this.max ) {
                Circle blood = new Circle(circle.getCenterX(), circle.getCenterY(), (int)(Math.random() * this.circle.getRadius()/2), Color.RED);
                this.circle.setRadius(this.circle.getRadius() -  CircleManager.deltaRadius);
                this.bloods.add(blood);
                root.getChildren().add(blood);
                this.count = 0;
                this.max = (int) (Math.random() * CircleManager.MAX_LIMIT);
            } else{
                this.count++;
            }
        }


    }

    public void HPZero(){

        this.RED = 255;
        this.GREEN = 0;

    }

    public void reset(){

        this.GREEN = 255;
        this.RED = 0;
        this.root.getChildren().removeAll(this.bloods);
        this.bloods = new ArrayList<>();
        this.circle.setRadius(this.initialRadius);

    }


}
