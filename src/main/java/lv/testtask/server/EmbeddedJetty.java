package lv.testtask.server;

import lv.testtask.WebAppInitializer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

public final class EmbeddedJetty {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedJetty.class);


    public static final int PORT = 8088;
    private Server jettyServer;

    public void startServer(){
        jettyServer = new Server(PORT);
        jettyServer.setHandler(getServerHandler());
        try {
            jettyServer.start();
        } catch (Exception e) {
            LOGGER.error("Failed to start embedded server", e);

        }
    }

    private Handler getServerHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.addEventListener(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                try {
                    new WebAppInitializer().onStartup(sce.getServletContext());
                } catch (ServletException e) {
                    LOGGER.error("Context initilalization failed", e);
                }
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {

            }
        });
        handler.setContextPath("/");
        return handler;
    }

    public void join(){
        try {
            jettyServer.join();
        } catch (InterruptedException e) {
            LOGGER.error("Failed to start embedded server", e);
        }
    }
}
