package org.example.model;

import org.example.model.manyToMany.Course;
import org.example.model.manyToMany.Student;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testManyToManyRelationship(){

        //Estudiantes
        Student student1 = new Student();
        student1.setName("Martín");
        Student student2 = new Student();
        student2.setName("Marina");

        //Cursos
        Course course1 = new Course();
        course1.setTitle("ADT");
        Course course2 = new Course();
        course2.setTitle("PSPR");

        List<Course> courses1 = new ArrayList<>();
        courses1.add(course1);
        courses1.add(course2);

        List<Course> courses2 = new ArrayList<>();
        courses2.add(course1);

        student1.setCourses(courses1);
        student2.setCourses(courses2);

        //Persistencia
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(course1);
        session.persist(course2);

        session.persist(student1);
        session.persist(student2);

        session.getTransaction().commit();

        //Probar si hibernate haya persistido
        assertNotNull(student1.getId());
        assertNotNull(student2.getId());

        assertNotNull(course1.getId());
        assertNotNull(course2.getId());

    }
}
