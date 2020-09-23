package com.blessy.dclmservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@Component
@ConfigurationProperties("denomination")
public class DenominationProperties implements Serializable {
    private String name;
    private String abv;
    private String country;
    private String founder;
    private String startDate;
    private String hq;
    private double gpsLat;
    private double gpsLong;
    private String logo;
    private String mission;
}
