package org.example.model;

import org.example.model.oneToMany.Employee;
import org.example.model.oneToOne.Vehicle;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeTest {

    @Test
    public void persistEmployee(){
        Employee newEmployee = new Employee();
        newEmployee.setName("Jorge");

        Vehicle newVehicle = new Vehicle();
        newVehicle.setPlate("1234ABC");

        newEmployee.setVehicle(newVehicle);

        //Session para realizar cualquier operacion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        session.persist(newEmployee);

        session.getTransaction().commit();

        assertNotNull(newEmployee.getName());


    }
}
