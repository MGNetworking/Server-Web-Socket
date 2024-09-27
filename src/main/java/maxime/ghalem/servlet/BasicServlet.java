package maxime.ghalem.servlet;

import maxime.ghalem.Request;
import maxime.ghalem.Response;

import java.io.PrintWriter;

public class BasicServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("BasicServlet init() ");
    }

    @Override
    public void doGet(Request request, Response response) {
        System.out.println("BasicServlet doGet");
        PrintWriter out = response.getPrintWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>This page comes to BasicServlet </h1>\n" +
                "<p>Thank you for your understand </p>\n" +
                "\n" +
                "</body>");

    }

    @Override
    public void doPost(Request request, Response response) {
        System.out.println("BasicServlet doPost");
    }
}
