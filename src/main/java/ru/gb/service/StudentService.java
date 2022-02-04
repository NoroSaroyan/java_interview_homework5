package ru.gb.service;

import ru.gb.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

public class StudentService implements IStudentService {
    private StudentDAO studentDao;

    public StudentService(StudentDAO studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    @Transactional
    public Student getById(Long id) {
        Student student = studentDao.getById(id);
        if (student != null) {
            return student;
        }
        return null;
    }

    @Override
    public void add(Student student) {
        studentDao.add(student);
    }

    @Override
    public void edit(Student student) {
        studentDao.edit(student);
    }

    @Override
    public void deleteById(Long id) {
        studentDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        studentDao.deleteALL();
    }

    public void addStudents(List<Student> students) {
        studentDao.addStudents(students);
    }

    public void printTotalCount() {
        System.out.println("Количество записей в БД: " + studentDao.countAll());
    }

    public Student rename(Long id, String newName) {

        if (studentDao.countAll() == 0) return null;
        Student student = studentDao.getById(id);
        if (student != null) {
            student.setName(newName);
            studentDao.merge(student);
            return student;
        }
        return null;
    }
}
