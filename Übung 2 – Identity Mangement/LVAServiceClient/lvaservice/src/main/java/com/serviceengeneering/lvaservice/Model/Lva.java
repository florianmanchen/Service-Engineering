package com.serviceengeneering.lvaservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "lvas")
@Data
public class Lva {

    @ManyToOne
    @JoinColumn(name = "lvaLeader_id", nullable = false)
    LvaLeader lvaLeader;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToMany
    List<Student> students;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
