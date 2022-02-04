package ru.gb.service;

import ru.gb.entity.Student;

import java.util.List;

public interface IStudentService {


    List<Student> findAll();

    Student getById(Long id);

    void add(Student student);

    void edit(Student student);

    void deleteById(Long id);

    void deleteAll();
}

