package codice_segreto;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;


public class CodiceSegreto extends Application {

    private PasswordField pswField;
    private Button btnCancel, btnMostra;
    private TextArea textArea;
    private Text lblTextArea;

    @Override
    public void start(Stage primaryStage) throws Exception {


        this.initGUI();
        Pane root = new HBox();
        ((HBox) root).setAlignment(Pos.CENTER);
        ((HBox) root).setSpacing(40);

        VBox sx = new VBox(this.pswField);
        GridPane tastiera = new GridPane();
        int riga = -1;
        for( int i = 9; i > 0; i-- ){

            Button btn = new Button("" + i);
            btn.setPrefSize(this.btnCancel.getPrefWidth(), this.btnCancel.getPrefHeight());
            btn.setOnAction( e -> {

                this.pswField.setText(this.pswField.getText().toString() + btn.getText().toString());

            });
            if( i % 3 == 0 )
                riga++;

            tastiera.add(btn, i % 3 == 0 ? 2 : i%3-1, riga);

        }
        tastiera.add(this.btnCancel, 0, 3);
        Button btn = new Button("0");
        btn.setPrefSize(this.btnCancel.getPrefWidth(), this.btnCancel.getPrefHeight());
        btn.setOnAction( e ->{

            this.pswField.setText(this.pswField.getText().toString() + btn.getText().toString());

        });
        tastiera.add(btn, 1, 3);
        tastiera.add(this.btnMostra, 2, 3);
        tastiera.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        sx.getChildren().add(tastiera);

        VBox dx = new VBox(this.lblTextArea, this.textArea);

        root.getChildren().addAll(sx, dx);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest( e ->{

            System.exit(0);

        });

        primaryStage.show();



    }

    private void initGUI(){

        this.pswField = new PasswordField();
        this.pswField.setEditable(false);
        this.pswField.setPrefSize(300, 33);

        this.btnCancel = new Button("C");
        this.btnCancel.setPrefSize(100,33);

        this.btnMostra = new Button("Mostra");
        this.btnMostra.setPrefSize(100,33);

        this.lblTextArea = new Text("Codice Digitato:");
        this.lblTextArea.setStroke(Color.CADETBLUE);
        this.textArea = new TextArea();
        this.textArea.setEditable(false);
        this.textArea.setWrapText(true);
        this.textArea.setPrefSize(600,100);
        this.textArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        this.btnMostra.setOnAction( e ->{

            this.textArea.setText(this.pswField.getText().toString());

        });

        this.btnCancel.setOnAction( e -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Delete");
            alert.setHeaderText("Are you sure you want to delete?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK){
                this.textArea.setText("");
                this.pswField.setText("");
            }

        });

    }
}
