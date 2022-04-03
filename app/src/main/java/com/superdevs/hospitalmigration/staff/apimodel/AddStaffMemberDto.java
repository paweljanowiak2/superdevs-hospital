package com.superdevs.hospitalmigration.staff.apimodel;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class AddStaffMemberDto {
    @NotNull
    private final String name;
    @NotNull
    private final LocalDate registrationDate;
}
