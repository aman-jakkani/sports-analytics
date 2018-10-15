package edu.sportanalytics.control;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import edu.sportanalytics.guiinterface.RestServer;

import java.io.*;


import java.net.URI;
import java.util.logging.Logger;

public class WebServer
{
    public static final Logger log = Logger.getLogger(WebServer.class.getName());

        //Singleton - one server is enough
        private static WebServer instance = null;

        private HttpServer server;

        private WebServer()
        {
            HttpServer server = RestServer.getInstance().getServer();
            server.createContext("/", new SimpleHttpHandler());
        }

        public static WebServer getInstance()
        {
            if(instance == null)
            {
                try
                {
                    instance = new WebServer();
                }
                catch (Exception e)
                {
                    log.severe("Error creating WebServer: " + e.getMessage());
                    instance=null;
                }
            }
            return instance;
        }

        public void close()
        {
            log.info("Closing WebServer");
            instance=null;
        }
}

class SimpleHttpHandler implements HttpHandler {

    public static final Logger log = Logger.getLogger(SimpleHttpHandler.class.getName());


    //HTML files must not start with "rest"!
    public void handle(HttpExchange t) throws IOException {
        String root = "./web";
        URI uri = t.getRequestURI();
        String path = uri.getPath();

        if(path.equals("/"))
        {
            log.info("Requesting root. Redirecting to index.html");
            path = "/index.html";
        }

        log.info("Received http request for path: " + path);

        File file = new File(root + path).getCanonicalFile();

        if (!file.isFile()) {
            // Object does not exist or is not a file: reject with 404 error.
            String response = "404 (Not Found)\n";
            log.severe(response);

            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            // Object exists and is a file: accept with response code 200.
            log.info("File found on server");

            String mime = "text/html";
            if (path.substring(path.length() - 3).equals(".js")) mime = "application/javascript";
            if (path.substring(path.length() - 3).equals("css")) mime = "text/css";

            Headers h = t.getResponseHeaders();
            h.set("Content-Type", mime);
            t.sendResponseHeaders(200, 0);

            OutputStream os = t.getResponseBody();
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0) {
                os.write(buffer, 0, count);
            }
            fs.close();
            os.close();

        }
    }
}
