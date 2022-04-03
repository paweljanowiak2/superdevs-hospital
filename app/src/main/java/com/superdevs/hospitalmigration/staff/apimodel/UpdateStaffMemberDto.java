package com.superdevs.hospitalmigration.staff.apimodel;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class UpdateStaffMemberDto {
    @NotNull
    private final String name;

    // I left it here because we do not have enough information about registration date. I assumed ability to correct human errors.
    @NotNull
    private final LocalDate registrationDate;
}
