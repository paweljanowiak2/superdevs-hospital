package com.superdevs.hospitalmigration.patient.apimodel;

import lombok.Value;

@Value
public class DeleteResultDto {
    private final int deletedRowsCount;
}
