package lv.testtask.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Profile("dev")
public class StandAloneDBConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() throws PropertyVetoException {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setPackagesToScan("lv.testtask.persistence.domain");
        Properties properties = new Properties();
        hibernateProperties(properties);

        factory.setHibernateProperties(properties);
        factory.setDataSource(dataSource());

        return factory;
    }

    @Bean
    public SessionFactory sessionFactory() throws PropertyVetoException {
        return sessionFactoryBean().getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws PropertyVetoException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory());
        return transactionManager;
    }

    private void hibernateProperties(Properties properties) {
        properties.setProperty("hibernate.show_sql", String.valueOf(true));
        properties.setProperty("hibernate.format_sql", String.valueOf(true));
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

    }


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }
}
