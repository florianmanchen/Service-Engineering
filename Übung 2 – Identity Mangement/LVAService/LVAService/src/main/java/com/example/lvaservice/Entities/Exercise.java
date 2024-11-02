package com.example.lvaservice.Entities;

import jakarta.persistence.*;

import javax.swing.text.Document;

@Entity
@Table(name = "exercise")
public class Exercise {

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "lvaLeader_id")
    LVALeader lvaLeader;

    @ManyToOne
    @JoinColumn(name = "lva_id")
    LVA lva;

    @Column(name = "feedback", nullable = false)
    String feedback;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Exercise() {
    }
}
