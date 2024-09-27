package maxime.ghalem.container;

import maxime.ghalem.Request;
import maxime.ghalem.Response;
import maxime.ghalem.servlet.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class SocketManager extends Thread {

    Socket socket = null;
    Map<String, HttpServlet> handler;

    public SocketManager(Socket socket, Map<String, HttpServlet> handler) {
        this.setSocket(socket);
        this.handler = handler;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        PrintWriter pr = null;
        System.out.println("------------------------");
        System.out.println("Running in thread, name:  " + Thread.currentThread().getName());
        System.out.println("Running in thread, id:" + Thread.currentThread().getId());
        System.out.println("------------------------");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {

            Request request = new Request(bufferedReader);

            if (!request.parse()) {
                System.out.println("Create response to client ");
                pr = new PrintWriter(socket.getOutputStream());
                pr.println("HTTP/1.1 500 Internal Server Error");
                pr.println("Content-Type: text/plain");
                pr.println();  // one line break for protocole http, the header
                pr.println("<html><body>can't process request</body></html>");
                pr.flush();

            } else {

                HttpServlet servlet = this.handler.get(request.getPath());

                if (servlet == null) {
                    pr = new PrintWriter(socket.getOutputStream());
                    pr.println("HTTP/1.1 404 Not Found");
                    pr.println("Content-Type: text/html");
                    pr.println(); // one line break for protocole http, the header
                    pr.println("<html><body>Not Found Servlet handler in your request</body></html>");
                    pr.flush();
                } else {
                    System.out.println("Create response to client ");
                    Response response = new Response(socket.getOutputStream());
                    PrintWriter out = response.getPrintWriter();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println(); // one line break for protocole http, the header
                    servlet.service(request, response);
                    out.flush();
                }

            }


            System.out.println("Methode" + request.getMethode());
            System.out.println("Path: " + request.getPath());
            System.out.println("header ---------");
            request.getHeaders().forEach((key, valus) -> System.out.println(key + " : " + valus));
            System.out.println("Request Parameters ---------");
            request.getRequestParameters().forEach((key, valus) -> System.out.println(key + " : " + valus));

        } catch (IOException ioe) {
            System.out.println("an error has occurred ");
            System.out.println(ioe.fillInStackTrace());

        } catch (Exception ex) {
            System.out.println("an unexpected error has occurred ");
            System.out.println(ex.fillInStackTrace());
        } finally {
            if (pr != null) {
                pr.close();
            }
            System.out.println("------------------------");
            System.out.println("Number of active threads: " + Thread.activeCount());
            System.out.println("------------------------");
        }
    }

    public void setSocket(Socket socket) throws IllegalArgumentException {
        if (socket != null) {
            this.socket = socket;
        } else {
            throw new IllegalArgumentException("this socket can't be null");
        }
    }
}
