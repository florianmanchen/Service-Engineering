package com.example.lvaservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Column(name = "fistName", nullable = false)
    String firstName;

    @Column(name = "lastName", nullable = false)
    String lastName;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Student() {
    }

    public Student(String firstName, String lastName, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
}
