package maxime.ghalem.container;

import maxime.ghalem.servlet.HttpServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    private final String config;
    private int port;
    private Map<String, HttpServlet> handler;

    public Main(String config, int port, Map<String, HttpServlet> handler) throws IOException {
        this.config = config;
        this.port = port;
        this.handler = handler;
    }

    /**
     * Start socket server
     *
     * @throws IOException
     */
    public void start() throws IOException {
        System.out.println("Start Socket server ");
        ServerSocket serverSocket = new ServerSocket(this.port);

        while (true) {

            System.out.println("waiting for request ... ");
            Socket socket = serverSocket.accept();
            System.out.println("create thread socket ");
            Thread handler = new SocketManager(socket, this.handler);
            System.out.println("Creates a new thread with this start methode");
            handler.start();
        }
    }

    /**
     * load properties file
     *
     * @throws IOException
     */
    private void loadPropertiesFile() throws IOException {
        InputStream input = getClass().getResourceAsStream(this.config);

        if (input == null) {
            throw new RuntimeException("Unable to find file : " + this.config);
        }
        Properties properties = new Properties();
        properties.load(input);

        System.out.println("Mapping handler socket");
        properties.forEach((key, value) -> {
            HttpServlet servlet = this.getServletInstance((String) value);
            servlet.init();
            this.handler.put((String) key, servlet);
        });

    }

    /**
     * Creates a new instance of a class that extends {@link HttpServlet} using Java's reflection API.
     *
     * @param className the fully qualified name of the class to instantiate
     * @return an instance of the class specified by {@code className}, cast to {@link HttpServlet}.
     * @throws RuntimeException if the class cannot be found, instantiated, or any reflection-related exception occurs.
     *                          The underlying exceptions include {@link InstantiationException}, {@link IllegalAccessException},
     *                          {@link InvocationTargetException}, {@link NoSuchMethodException}, and {@link ClassNotFoundException}.
     */
    private HttpServlet getServletInstance(String className) throws RuntimeException {
        try {
            return (HttpServlet) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Main container = new Main("/config.properties", 8888, new HashMap<>());
        container.loadPropertiesFile();

        // add event when shutdown server
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                container.handler.forEach((key,httpServlet) -> {
                    httpServlet.destroy();
                });
            }
        });

        container.start();
    }

}