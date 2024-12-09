package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class AppConfig {
    @Value("${app.db.driverClassName}")
    private String driverClassName;

    @Value("${app.db.url}")
    private String url;

    @Value("${app.db.username}")
    private String username;

    @Value("${app.db.password}")
    private String password;

    @Value("${app.db.initialSize}")
    private int initialSize;

    @Value("${app.db.maxTotal}")
    private int maxTotal;

    @Value("${app.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${app.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${app.hibernate.show_sql}")
    private String showSql;

    @Value("${app.hibernate.format_sql}")
    private String formatSql;

    @Value("${app.hibernate.package_to_scan}")
    private String packageToScan;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(packageToScan);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}


