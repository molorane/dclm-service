package com.blessy.dclmservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class ChurchCategory {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotEmpty(message = "church category required.")
    @Column(nullable = false, unique = true)
    private String name;
}
