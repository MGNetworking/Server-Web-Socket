package maxime.ghalem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private final int port;

    public Main(int port) {
        this.port = port;
        System.out.println("this port container is :" + this.port);
    }

    public void start() throws IOException {
        System.out.println("Start container ");
        ServerSocket serverSocket = new ServerSocket(this.port);

        while (true) {

            System.out.println("waiting for external request to create");
            Socket socket = serverSocket.accept();
            System.out.println("manage multi threading de socket");
            Thread handler = new SocketManager(socket);
            System.out.println("Creates a new thread with this start methode");
            handler.start();
        }
    }

    public static void main(String[] args) throws IOException {
        Main container = new Main(8888);
        container.start();
    }

}