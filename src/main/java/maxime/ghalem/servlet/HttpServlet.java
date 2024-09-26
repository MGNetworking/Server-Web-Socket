package maxime.ghalem.servlet;

public abstract class HttpServlet {

    public void init(){
        System.out.println("Initialize Http servlet");
    }

    public void service(){ // TODO: Request, Response Object as Parameters

    }

    public void doGet(){
        System.out.println("HttpServlet doGet");
    }

    public void doPost(){
        System.out.println("HttpServlet doPost");
    }
}
