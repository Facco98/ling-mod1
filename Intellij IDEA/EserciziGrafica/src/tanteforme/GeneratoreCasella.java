package tanteforme;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public enum GeneratoreCasella {

    UNO(() ->{

        Button btnC = new Button("C");
        Button btnX = new Button("X");
        Button btnO = new Button("O");

        Casella casella = new Casella(btnC, btnX, btnO);

        btnC.setOnAction((event) ->{

            casella.pulisciPane();

        });

        btnX.setOnAction((event) ->{

            Line line1 = new Line(0,0,20,20);
            Line line2 = new Line(0,20,20,0);
            Shape cross = Shape.union(line1, line2);
            cross.setStroke(Color.RED);
            casella.addToPane(cross);
            casella.setValue(Casella.X);

        });

        btnO.setOnAction((event) ->{

            Circle circle = new Circle(10);
            circle.setFill(Color.RED);
            casella.addToPane(circle);
            casella.setValue(Casella.C);

        });
        return casella;

    }),
    DUE( () ->{


        Button btnR = new Button("R");
        Button btnC = new Button("C");

        Casella casella = new Casella(btnC, btnR);

        btnC.setOnAction((event) ->{

            casella.pulisciPane();

        });

        btnR.setOnAction((event) ->{

            double random = Math.random();
            Shape shape;
            if( random <= 0.5 ){

                shape = new Circle(10);
                casella.setValue(Casella.C);

            } else{

                Line line1 = new Line(0,0,20,20);
                Line line2 = new Line(0,20,20,0);
                shape = Shape.union(line1, line2);
                casella.setValue(Casella.X);

            }
            shape.setFill(Color.RED);
            shape.setStroke(Color.RED);
            casella.addToPane(shape);

        });
        return casella;

    });

    private Generatore<Casella> generatore;

    GeneratoreCasella( Generatore<Casella> generatore ){

        this.generatore = generatore;

    }

    public Casella getCasella(){

        return this.generatore.genera();

    }


}

interface Generatore<T>{

    public T genera();

}