package lv.testtask;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

import java.io.IOException;

public class HSQLDbServer implements SmartLifecycle {

    private final Logger LOG = LoggerFactory.getLogger(HSQLDbServer.class);
    private HsqlProperties properties;
    private Server server;
    private boolean running = false;

    public HSQLDbServer(HsqlProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public void start() {
        if (server == null) {
            LOG.info("Starting HSQLDB server");
            server = new Server();

            try {
                server.setProperties(properties);
                server.start();
                running = true;
            } catch (IOException e) {
                LOG.error("Error starting server", e);
            } catch (ServerAcl.AclFormatException e) {
                LOG.error("Error starting server", e);
            }
        }
    }

    @Override
    public void stop() {
        if (server != null) {
            LOG.info("Stopping HSQLDB server");
            server.stop();
            running = false;
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
