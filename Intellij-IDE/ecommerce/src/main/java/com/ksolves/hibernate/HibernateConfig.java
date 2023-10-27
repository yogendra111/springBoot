package com.ksolves.hibernate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

}

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
////        sessionFactory.setPackagesToScan("com.ksolves.entities");
//        sessionFactory.setAnnotatedClasses(Employee.class, Address.class, Contact.class, Project.class);
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("sa");
//
//        return dataSource;
//    }
//
//    private final Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(
//                "hibernate.hbm2ddl.auto", "create-drop");
//        hibernateProperties.setProperty(
//                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//
//        return hibernateProperties;
//    }

//}
//    @Value("${spring.datasource.username}")
//    String username;
//    @Value("${spring.datasource.password}")
//    String password;
//    @Value("${datasource.url}")
//    String url;
//    @Value("${spring.datasource.driverClassName}")
//    String driverClass;
//    @Value("${spring.jpa.properties.hibernate.dialect}")
//    String dialect;
//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    String hbm2ddl;
//
//    @Bean
//    DataSource dataSource() throws SQLException {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriverClassName(driverClass);
//        return dataSource;
//    }
//
//    @Bean
//    SessionFactory sessionFactory() throws SQLException {
//        LocalSessionFactoryBean localSessionFactoryBean =
//                new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource());
//        localSessionFactoryBean.setAnnotatedPackages("com.ksolves.entities.*");
//        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
//        return localSessionFactoryBean.getObject();
//    }
//
//    Properties hibernateProperties(){
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", dialect);
//        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
//        return properties;
//    }
