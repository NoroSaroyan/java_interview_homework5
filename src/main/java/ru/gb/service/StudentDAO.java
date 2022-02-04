package ru.gb.service;

import ru.gb.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDAO {

    private SessionFactory factory;

    public StudentDAO(SessionFactory factory) {

        this.factory = factory;
    }

    public List<Student> findAll() {

        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Student> studentsList = (List<Student>) session.createQuery("select s from Student s").list();
            return studentsList;
        } finally {
            session.getTransaction().commit();

        }
    }

    public Student getById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    public boolean delete(Student entity) {
        return deleteById(entity.getId());
    }

    public boolean deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Student s where s.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    public void add(Student student) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(student);
        } finally {
            session.getTransaction().commit();
        }
    }

    public List<Student> addStudents(List<Student> students) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            students.forEach(session::merge);
            session.getTransaction().commit();
            return students;
        }
    }

    public void edit(Student student) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        }
    }

    public boolean deleteALL() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Student").executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    public Long countAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Long countResult = (Long) session.createQuery("select count(s) from Student s")
                    .uniqueResult();
            session.getTransaction().commit();
            return countResult;
        }
    }

    public Student merge(Student entity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = (Student) session.merge(entity);
            session.getTransaction().commit();
            return student;
        }
    }

}