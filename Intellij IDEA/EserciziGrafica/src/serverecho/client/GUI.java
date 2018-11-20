package serverecho.client;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Optional;

public class GUI extends Application{

    private TextField txtMessage;
    private Button btnSend;
    private MenuBar menuBar;
    private MenuItem[] menuItems;
    private Socket socket;

    private String serverHost = "localhost";
    private int serverPort = 8080;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.initGUI();

        BorderPane root = new BorderPane();
        VBox center = new VBox(this.txtMessage, this.btnSend);
        center.setAlignment(Pos.CENTER);
        center.setFillWidth(false);
        center.setSpacing(5);

        root.setCenter(center);
        root.setPrefSize(400,250);
        Scene scene = new Scene(root);

        this.menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }

    private void initGUI(){

        this.txtMessage = new TextField();
        this.txtMessage.setPrefSize(200, 25);

        this.btnSend = new Button("Send");
        this.btnSend.setOnAction(e ->{

            try {
                if( this.socket != null ) {
                    BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                    socketOut.write(txtMessage.getText());
                    socketOut.newLine();
                    socketOut.flush();
                }
            } catch (IOException e1) {
                new Alert(Alert.AlertType.ERROR, "Non riesco a inviare il messaggio al server");
            }

        });

        this.menuBar = new MenuBar();
        this.menuItems = new MenuItem[3];
        this.menuItems[0] = new MenuItem("Server");
        this.menuItems[0].setOnAction(e ->{

            TextInputDialog dialog = new TextInputDialog(this.serverHost);
            dialog.setTitle("Server host");
            dialog.setContentText("Enter the server ip address:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(host -> this.serverHost = host);


        });

        this.menuItems[1] = new MenuItem("Port");
        this.menuItems[1].setOnAction(e ->{

            TextInputDialog dialog = new TextInputDialog(""+this.serverPort);
            dialog.setTitle("Server port");
            dialog.setContentText("Enter the server port:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(port -> {

                try{

                    this.serverPort = Integer.parseInt(port);

                } catch( Exception ex ){

                    new Alert(Alert.AlertType.ERROR, "Non hai inserito un numero di porta valido").showAndWait();

                }

            });


        });

        this.menuItems[2] = new MenuItem("Connect");
        this.menuItems[2].setOnAction(e ->{

            try {
                if( this.socket != null ){
                    this.socket.close();
                }
                this.socket = new Socket(this.serverHost, this.serverPort);
            } catch (IOException e1) {
                new Alert(Alert.AlertType.ERROR, "Non riesco a connettermi all'host specificato").showAndWait();
            }

        });
        Menu setup = new Menu("Setup");
        setup.getItems().addAll(menuItems);
        this.menuBar.getMenus().add(setup);



    }
}
