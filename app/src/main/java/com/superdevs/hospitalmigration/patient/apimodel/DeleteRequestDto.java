package com.superdevs.hospitalmigration.patient.apimodel;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class DeleteRequestDto {
    @NotNull
    @Past
    private final LocalDate from;

    @NotNull
    private final LocalDate to;
}
