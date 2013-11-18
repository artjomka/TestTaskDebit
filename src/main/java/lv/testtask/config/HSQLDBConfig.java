package lv.testtask.config;

import lv.testtask.HSQLDbServer;
import org.hsqldb.persist.HsqlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class HSQLDBConfig {


    @Bean(initMethod = "start")
    public HSQLDbServer hsqldb(){
        HsqlProperties properties = new HsqlProperties();
        properties.setProperty("server.database.0", "d:/temp/taskbase");
        properties.setProperty("server.dbname.0", "taskbase");
        properties.setProperty("server.remote_open", "taskbase");
        HSQLDbServer hsqldb = new HSQLDbServer(properties);
        return  hsqldb;
    }


}
