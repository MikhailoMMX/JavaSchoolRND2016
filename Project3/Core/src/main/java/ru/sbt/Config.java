package ru.sbt;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import org.hibernate.cfg.Configuration;

@EnableTransactionManagement
@EnableJpaRepositories
public class Config {
    @Bean
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    @Bean
    public static Scanner getScanner(){
        return new Scanner(System.in);
    }

    @Bean
    public static SessionFactory sessionFactory(DataSource dataSource) {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }


//    public static HibernateTransactionManager transactionManager(DataSource dataSource, SessionFactory sessionFactory){
//        final HibernateTransactionManager tr = new HibernateTransactionManager();
//        tr.setDataSource(dataSource);
//        tr.setSessionFactory(sessionFactory);
//        return tr;
//    }

    public static JpaTransactionManager jpaTransactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory){
        final JpaTransactionManager tr = new JpaTransactionManager();
        tr.setDataSource(dataSource);
        tr.setEntityManagerFactory(entityManagerFactory);
        return tr;
    }

    @Bean
    public static EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("ru.sbt.data");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
