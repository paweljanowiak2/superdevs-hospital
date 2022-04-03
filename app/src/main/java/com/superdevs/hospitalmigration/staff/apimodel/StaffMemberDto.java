package com.superdevs.hospitalmigration.staff.apimodel;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class StaffMemberDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
    private final LocalDate registrationDate;
}
