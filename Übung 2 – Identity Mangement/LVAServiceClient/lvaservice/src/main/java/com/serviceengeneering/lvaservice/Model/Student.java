package com.serviceengeneering.lvaservice.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Column(name = "fistName", nullable = false)
    String firstName;

    @Column(name = "lastName", nullable = false)
    String lastName;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
