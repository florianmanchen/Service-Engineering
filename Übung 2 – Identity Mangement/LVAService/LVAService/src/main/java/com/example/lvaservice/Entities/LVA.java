package com.example.lvaservice.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lva")
public class LVA {

    @ManyToOne
    @JoinColumn(name = "lvaLeader_id", nullable = false)
    LVALeader lvaLeader;

    @ManyToMany
    List<Student> students;

    @Column(name = "name", nullable = false)
    String name;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public LVA() {
    }
}
