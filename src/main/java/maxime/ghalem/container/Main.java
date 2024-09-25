package maxime.ghalem.container;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Main {

    private final String config;
    private int port;

    public Main(String config) throws IOException {
        this.config = config;
        loadPropertiesFile();
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

    private void loadPropertiesFile() throws IOException {
        InputStream input = getClass().getResourceAsStream(this.config);

        if (input == null) {
            throw new RuntimeException("Unable to find file : " + this.config);
        }
        Properties properties = new Properties();
        properties.load(input);

        properties.forEach((key, value) -> System.out.println(key + ": " + value));
        this.port = Integer.parseInt(properties.getProperty("port"));
    }

    public static void main(String[] args) throws IOException {
        Main container = new Main("/config.properties");
        container.start();
    }

}