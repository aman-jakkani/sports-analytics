package edu.sportanalytics.guiinterface;

import edu.sportanalytics.control.WebServer;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;

import java.net.URI;
import java.util.logging.Logger;

public class RestServer
{
    public static final Logger log = Logger.getLogger(RestServer.class.getName());

    //Singleton - one server is enough
    private static RestServer instance = null;

    private HttpServer server;
    private final String serverURI = "http://localhost:8081/rest";

    private RestServer()
    {
        ResourceConfig rc = new ResourceConfig().packages("edu.sportanalytics.guiinterface");
        log.info("Starting http server for rest resources");
        server = JdkHttpServerFactory.createHttpServer(URI.create(serverURI), rc);
    }

    public static RestServer getInstance()
    {
        if(instance == null)
        {
            //try
            //{
                instance = new RestServer();
                WebServer.getInstance(); //ini
            //}
            //catch (Exception e)
            //{
            //    log.severe("Error creating RestServer: " + e.getMessage());
            //    instance=null;
            //}
        }
        return instance;
    }

    public HttpServer getServer()
    {
        return server;
    }

    public void close()
    {
        log.info("Closing RestServer");
        server.stop(0);
        WebServer.getInstance().close();
        instance=null;
    }

}
