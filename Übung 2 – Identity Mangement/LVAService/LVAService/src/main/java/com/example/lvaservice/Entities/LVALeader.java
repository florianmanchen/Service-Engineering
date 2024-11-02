package com.example.lvaservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "lvaLeader")
public class LVALeader {
    @Column(name = "fistName", nullable = false)
    String firstName;

    @Column(name = "lastName", nullable = false)
    String lastName;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public LVALeader() {
    }

    public LVALeader(String firstName, String lastName, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
}
