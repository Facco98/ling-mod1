package serverecho.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SessionHandler implements Runnable {

    private Socket clientSocket;

    public SessionHandler(Socket clientSocket){

        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {

        try {
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            String msg;
            while((msg = socketIn.readLine()) != null ){

                System.out.println("[" + this.clientSocket.getRemoteSocketAddress().toString() + "] " + msg );

            }
            System.out.println("[" + this.clientSocket.getRemoteSocketAddress().toString() + "] Disconnesso");
        } catch (IOException e) {
            System.out.println("[" + this.clientSocket.getRemoteSocketAddress().toString() + "] Disconnesso");
        }


    }
}
