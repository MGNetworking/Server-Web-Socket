package maxime.ghalem.servlet;

public class BasicServlet extends HttpServlet {

    @Override
    public void doGet() {
        System.out.println("BasicServlet doGet");
    }

    @Override
    public void doPost() {
        System.out.println("BasicServlet doPost");
    }
}
