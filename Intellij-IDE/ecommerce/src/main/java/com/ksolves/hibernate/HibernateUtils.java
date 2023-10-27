package com.ksolves.hibernate;

import com.ksolves.entities.Address;
import com.ksolves.entities.Contact;
import com.ksolves.entities.Employee;
import com.ksolves.entities.Project;
import lombok.Data;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtils {

    public static SessionFactory sessionFactory;

//    public static Session getCurrentSession() {
//    public HibernateUtils() {
    static {
        // Hibernate 5.4 SessionFactory example without XML
        Map<String, Object> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernatedb");
        settings.put(Environment.USER, "myuser");
        settings.put(Environment.PASS, "mypassword");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.FORMAT_SQL, "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Employee.class);
        metadataSources.addAnnotatedClass(Address.class);
        metadataSources.addAnnotatedClass(Contact.class);
        metadataSources.addAnnotatedClass(Project.class);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4)
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

}
