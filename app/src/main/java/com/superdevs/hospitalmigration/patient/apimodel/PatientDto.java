package com.superdevs.hospitalmigration.patient.apimodel;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PatientDto {
    private final Long id;
    private final String name;
    private final int age;
    private final LocalDate lastVisitDate;
}
