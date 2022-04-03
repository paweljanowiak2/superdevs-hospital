package com.superdevs.hospitalmigration.patient.service;

import com.superdevs.hospitalmigration.patient.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

public class PatientCsvMapper {
    private static final String[] HEADERS = {"id", "name", "age", "last_visit_date"};

    public byte[] toCSVByteArray(Patient patient) {
        var out = new ByteArrayOutputStream();
        var outWriter = new OutputStreamWriter(out);
        try (CSVPrinter printer = new CSVPrinter(outWriter, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            printer.printRecord(patient.getId(), patient.getName(), patient.getAge(), patient.getLastVisitDate());
        } catch (IOException e) {
            throw new IllegalStateException("Could not serialize to CSV.", e);
        }
        return out.toByteArray();
    }
}
