package com.blessy.dclmservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    FEMALE("Female"),
    MALE("Male");

    @Getter
    private final String displayValue;
}
