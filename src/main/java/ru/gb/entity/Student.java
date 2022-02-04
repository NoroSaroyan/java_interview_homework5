package ru.gb.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "mark")
    private String mark;


    public Student(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    public Student(int id, String name, String mark) {
        this.id =id;
        this.name = name;
        this.mark = mark;
    }

    public Student(String name, long l) {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}