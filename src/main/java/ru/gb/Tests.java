package ru.gb;

import ru.gb.entity.Student;
import ru.gb.util.HibernateUtil;
import ru.gb.service.StudentDAO;
import ru.gb.service.StudentService;
import org.hibernate.SessionFactory;

import java.util.List;

public class Tests {

    private StudentService studentService;

    public Tests() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        StudentDAO repository = new StudentDAO(factory);
        studentService = new StudentService(repository);
    }

    public void start() {

        System.out.println("\nCreating list of students in database with 10 elements in it...");
        for (int i = 1; i <= 10; i++) {
            studentService.add(new Student("Student_" + i, "#_" + (Math.random() * 10)));
        }
        studentService.printTotalCount();
        System.out.println("\nDeleting the list of 10 students from database...");
        studentService.deleteAll();
        studentService.printTotalCount();
        System.out.println();

        System.out.println("\nCreating a new record of 1000 students in database");
        for (int i = 1; i <= 1000; i++) {
            studentService.add(new Student("Student_" + i, "#_" + (Math.random() * 10)));
        }
        studentService.printTotalCount();
        List<Student> studentsList = studentService.findAll();
        Long id = studentsList.get(2).getId();
        System.out.println("\nDeleting of 3 students, Student_Id = " + id);
        studentService.deleteById(id);
        String newName = "0000";
        studentsList = studentService.findAll();
        Student student = studentsList.get(studentsList.size() - 2);
        System.out.println("\nUpdating the name of penultimate in the list");
        id = student.getId();
        System.out.println("\nold name – " + student.getName() + " new name – " + newName);
        studentService.rename(id, newName);
        System.out.println(studentService.getById(id));
        studentService.printTotalCount();
        System.out.println("\nDeleting all records from database");
        studentService.deleteAll();
        System.out.println("\nClosing HibernateUtil...");
        HibernateUtil.shutdown();
    }
}