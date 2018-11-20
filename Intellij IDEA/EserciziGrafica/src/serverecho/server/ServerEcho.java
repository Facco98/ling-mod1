package serverecho.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEcho {

    public static void main(String[] args){

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Inserisci su che porta ascolterò");
            int port = Integer.parseInt(keyboard.readLine());
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("ServerEcho in ascolto sulla porta " + port);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[" + clientSocket.getRemoteSocketAddress().toString()+"] Connesso");
                new Thread(new SessionHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("La porta scelta non è disponibile");
        }


    }

}
