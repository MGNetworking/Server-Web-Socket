package maxime.ghalem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class SocketManager extends Thread {

    Socket socket = null;

    public SocketManager(Socket socket) {
        this.setSocket(socket);
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

            String line = bufferedReader.readLine();

            while (line != null && !line.isEmpty()) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            System.out.println("Create response to client ");
            pr = new PrintWriter(socket.getOutputStream());
            pr.println("HTTP/1.1 200 OK");
            pr.println("Content-Type: text/html");
            pr.println();  // one line break for protocole http, the header

            pr.println("<html><body>");
            pr.println("Time : " + LocalDateTime.now());
            pr.println("</body></html>");

            pr.flush();

        } catch (IOException ioe) {
            System.out.println("an error has occurred ");
            ioe.fillInStackTrace();

        } catch (Exception ex) {
            System.out.println("an unexpected error has occurred ");
            ex.fillInStackTrace();
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
