package org.mypos.domain.repository;

import org.hibernate.Session;
import org.mypos.domain.Employee;
import org.mypos.domain.util.HibernateUtil;

import java.util.*;

public class EmployeeRepository {


    public static void main(String[] args) {
        EmployeeRepository mgr = new EmployeeRepository();

        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("Employee - "+(Math.random()*100));
        } else if (args[0].equals("list")) {
            List employees = mgr.listEvents();
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = (Employee) employees.get(i);
                System.out.println("Event: " + employee.getName());
            }
        }

        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setName(name);
        session.save(employee);

        session.getTransaction().commit();
    }

    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Employee").list();
        session.getTransaction().commit();
        return result;
    }

}
