package org.example.model;

import org.example.model.oneToMany.Department;
import org.example.model.oneToMany.Employee;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepartmentTest {

    @Test
    public void persistDepartment(){
        Department newDepartment = new Department();
        newDepartment.setName("Ventas");

        Employee employee1 = new Employee();
        employee1.setName("Celestino");
        Employee employee2 = new Employee();
        employee2.setName("Rigoberta");

        // Configura la relación bidireccional directamente en el test
        employee1.setDepartment(newDepartment);
        employee2.setDepartment(newDepartment);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        newDepartment.setEmployees(employees);

        /*
        //Con el método creado
        newDepartment.addEmployee(employee1);
        newDepartment.addEmployee(employee2);
        */
        //Session para realizar cualquier operacion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(newDepartment);

        session.getTransaction().commit();

        assertNotNull(newDepartment.getId());
        assertNotNull(employee1.getId());

        assertNotNull(employee2.getId());


    }
}
