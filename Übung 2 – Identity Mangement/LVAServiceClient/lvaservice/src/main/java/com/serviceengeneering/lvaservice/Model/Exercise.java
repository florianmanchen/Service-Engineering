package com.serviceengeneering.lvaservice.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exercises")
@Data
public class Exercise {

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "lvaLeader_id")
    LvaLeader lvaLeader;

    @ManyToOne
    @JoinColumn(name = "lva_id")
    Lva lva;

    @Column(name = "submission")
    String submission;

    @Column(name = "feedback")
    String feedback;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
