package com.blessy.dclmservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "State name required.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotEmpty(message = "state abv required.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 4, message = "State abv must have min 2 and max 4 characters.")
    private String abv;

    @ManyToOne
    @JoinColumn(name="country_id", nullable=false)
    private Country country;

    @OneToMany(mappedBy="state", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

}
