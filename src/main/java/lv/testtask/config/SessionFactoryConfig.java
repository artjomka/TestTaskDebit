package lv.testtask.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.beans.PropertyVetoException;
import java.util.Properties;


@Profile("production")
@Configuration
@PropertySource(value = {"classpath:/hibernate.properties", "classpath:/datasource.properties"})
@EnableTransactionManagement
public class SessionFactoryConfig {


    @Resource
    private Environment environment;

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

    private void hibernateProperties(Properties properties) {
        properties.setProperty("hibernate.show_sql", String.valueOf(true));
        properties.setProperty("hibernate.format_sql", String.valueOf(true));
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
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

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        final ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(environment.getProperty("url"));
        comboPooledDataSource.setUser(environment.getProperty("user"));
        comboPooledDataSource.setPassword(environment.getProperty("password"));
        comboPooledDataSource.setDriverClass(environment.getProperty("driverClass"));

        return comboPooledDataSource;
    }

}
