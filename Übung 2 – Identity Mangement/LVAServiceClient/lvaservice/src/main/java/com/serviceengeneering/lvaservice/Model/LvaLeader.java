package com.serviceengeneering.lvaservice.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lvaLeader")
@Data
public class LvaLeader {
    @Column(name = "fistName", nullable = false)
    String firstName;

    @Column(name = "lastName", nullable = false)
    String lastName;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
