package com.ksolves.services;

import com.ksolves.entities.Employee;
import com.ksolves.entities.Project;
import com.ksolves.hibernate.HibernateUtils;
import lombok.NoArgsConstructor;
import org.hibernate.*;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class HibernateService {

    SessionFactory sessionFactory = HibernateUtils.sessionFactory;

//    @Autowired
//    static SessionFactory sessionFactory;
//
//    static {
//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//    }

    public Long add(Employee employee){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Employee : " + employee);
        return employee.getId();
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
//            String query = "Select * from _employees where e_id = " + id;
//            session.createQuery();
            session.delete(session.get(Employee.class, id));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public Employee get(Long id){

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Employee e " +
                     "LEFT JOIN FETCH e.contact c " +
                     "LEFT JOIN FETCH e.projects p " +
                     "LEFT JOIN FETCH e.addresses a " +
                     "WHERE e.id = :employeeId";

        SelectionQuery query = session.createSelectionQuery(hql);
        query.setParameter("employeeId", id);
        Employee employee = (Employee) query.getSingleResult();
        Set<Project> projects = employee.getProjects();
        System.out.println("Projects : ");
        projects.stream().forEach(System.out::println);

//        query.getResultList().stream().forEach(System.out::println);
//            employee = session.get(Employee.class, id);

        tx.commit();
        session.close();
        System.out.println("Employee : " + employee);
        return employee;
    }
    public List<Employee> getAll(){

        Session session = sessionFactory.openSession();

        String hql = "FROM Employee e " +
                "LEFT JOIN FETCH e.contact c " +
                "LEFT JOIN FETCH e.addresses a";

        SelectionQuery query = session.createSelectionQuery(hql);
        List<Employee> employee = query.getResultList();
        session.close();
        return employee;
    }

    public Employee update(Long id){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Employee employee = null;
        try {
            tx = session.beginTransaction();
            employee = session.get(Employee.class, id);
            employee.setName("Ramesh");
            employee.setDesignation("Java Developer");
            session.persist(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

}
