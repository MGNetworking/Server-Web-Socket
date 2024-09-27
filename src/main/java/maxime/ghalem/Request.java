package maxime.ghalem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private BufferedReader reader;
    private String methode;
    private String path;
    private Map<String, String> headers;
    private Map<String, String> requestParameters;

    public Request(BufferedReader reader) {
        this.reader = reader;
        this.headers = new HashMap<>();
        this.requestParameters = new HashMap<>();
    }

    public String getMethode() {
        return methode;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public String getRequestParameters(String name) {
        return this.requestParameters.get(name);
    }

    private void parsRequestParameter(String params) { // user=max&password=123

        for (String param : params.split("&")) {
            String[] valuesParams = param.split("=");
            this.requestParameters.put(valuesParams[0], valuesParams[1]);
        }
    }

    /**
     * Parse the HTTP request from the input stream and extracts the method, path, headers, and request parameters.
     *
     * @return true if the request was successfully parsed and contains a valid HTTP structure (method, path, and headers),
     * *         false if the request is malformed or missing required components.
     * @throws IOException if an input or output exception occurs while reading the request.
     */
    public boolean parse() throws IOException {
        String line = reader.readLine();

        if (line != null) {

            String[] firsLine = line.split(" ");
            // check content parameter = GET /service?user=max&password=1234 HTTP/1.1
            if (firsLine.length != 3) return false;

            // get Methode GET / POST etc ...
            this.methode = firsLine[0];

            // get path / request parameter
            String url = firsLine[1];
            int index = url.indexOf("?");

            if (index > -1) {
                this.path = url.substring(0, index);
                this.parsRequestParameter(url.substring(index + 1));
            } else {
                this.path = url;
            }

            // get header parameter
            while (!(line = reader.readLine().trim()).isEmpty()) {
                int indexReferer = line.indexOf(":");
                this.headers.put(line.substring(0, indexReferer), line.substring(indexReferer + 1));
            }

            if ("POST".equals(this.methode)) {
                StringBuilder builder = new StringBuilder();
                while (reader.ready()) {
                    builder.append((char) reader.read());
                }
                this.parsRequestParameter(builder.toString());
            }

        } else {
            return false;
        }

        return true;

    }
}
