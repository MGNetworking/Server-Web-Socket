package maxime.ghalem.servlet;

import maxime.ghalem.Request;
import maxime.ghalem.Response;

import java.io.PrintWriter;

public class SignupServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("SignupServlet init() ");
    }

    @Override
    public void doGet(Request request, Response response) {
        System.out.println("SignupServlet doGet");
        PrintWriter out = response.getPrintWriter();
        out.println(
                "<html>\n" +
                        "<body>" +
                        "<form method=\"post\">\n" +
                        "  <label for=\"fname\">First name:</label><br>\n" +
                        "  <input type=\"text\" id=\"fname\" name=\"fname\" value=\"Maxime\"><br>\n" +
                        "  <label for=\"lname\">Last name:</label><br>\n" +
                        "  <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Ghalem\"><br><br>\n" +
                        "  <input type=\"submit\" value=\"Submit\">\n" +
                        "</form> " +
                        "</body>\n" +
                        "</html>");
    }

    @Override
    public void doPost(Request request, Response response) {
        System.out.println("SignupServlet doPost");
        PrintWriter out = response.getPrintWriter();
        out.println("<html>\n" +
                "<body> " +
                "Hello " + request.getRequestParameters("fname") + " " +
                request.getRequestParameters("lname") +
                "</body>\n" +
                "</html>");
    }

    @Override
    public void destroy() {
        System.out.println("SignupServlet destroy() ");
    }
}
