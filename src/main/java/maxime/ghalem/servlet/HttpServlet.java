package maxime.ghalem.servlet;

import maxime.ghalem.Request;
import maxime.ghalem.Response;

public abstract class HttpServlet {

    public void init() {
        System.out.println("HttpServlet init() ");
    }

    public void service(Request request, Response response) {

        String methode = request.getMethode();

        if ("GET".equals(methode)) {
            this.doGet(request, response);
        } else if ("POST".equals(methode)) {
            this.doPost(request, response);
        } else {
            throw new RuntimeException("Methode is not Supported !");
        }

    }

    public void doGet(Request request, Response response) {
        System.out.println("HttpServlet doGet");
    }

    public void doPost(Request request, Response response) {
        System.out.println("HttpServlet doPost");
    }
}
