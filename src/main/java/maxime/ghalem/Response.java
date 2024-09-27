package maxime.ghalem;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {

    private OutputStream outputStream;
    private PrintWriter printWriter;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.printWriter = new PrintWriter(outputStream);
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public PrintWriter getPrintWriter() {
        return this.printWriter;
    }
}
