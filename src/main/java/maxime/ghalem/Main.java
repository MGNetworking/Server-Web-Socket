package maxime.ghalem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Main {

    private int port;

    public Main(int port) {
        this.port = port;
        System.out.println("this port container is :" + this.port);
    }

    public void start() throws IOException {
        System.out.println("Start container ");
        ServerSocket serverSocket = new ServerSocket(this.port);

        System.out.println("listen to request");
        Socket socket = serverSocket.accept();

        System.out.println("retrieve customer data stream");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = bufferedReader.readLine();

        while (! line.isEmpty()){
            System.out.println(line);
            line = bufferedReader.readLine();
        }

        System.out.println("Create response to client ");
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println("HTTP/1.1 200 OK");
        pr.println("Content-Type: text/html");
        pr.println();  //

        pr.println("<html><body>");
        pr.println("Time : " + LocalDateTime.now());
        pr.println("</body></html>");

        pr.flush();

    }

    public static void main(String[] args) throws IOException {
        Main container = new Main(8888);
        container.start();
    }

}