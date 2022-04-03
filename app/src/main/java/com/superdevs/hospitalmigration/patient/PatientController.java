package com.superdevs.hospitalmigration.patient;

import com.superdevs.hospitalmigration.patient.apimodel.DeleteRequestDto;
import com.superdevs.hospitalmigration.patient.apimodel.DeleteResultDto;
import com.superdevs.hospitalmigration.patient.apimodel.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {
    private final PatientFacade patientFacade;

    // in the task the endpoint was specific, no query parameters, so I decided to do like this
    @GetMapping("/patients/all-up-to-two-years-old")
    public List<PatientDto> fetchAllPatientsUpToTwoYearsOld() {
        return patientFacade.fetchAllPatientsUpToTwoYearsOld();
    }

    @GetMapping(value = "/patients/{id}", produces = "application/csv")
    public void getPatientAsCSV(HttpServletResponse response, @PathVariable Long id) throws IOException {
        var csvByteArray = patientFacade.getPatientAsCSV(id);
        FileCopyUtils.copy(new ByteArrayInputStream(csvByteArray), response.getOutputStream());
        response.setContentType("application/csv");
    }

    @PostMapping("patients/delete-by-last-visited")
    public DeleteResultDto deletePatientsBetweenLastVisitRange(@Validated @RequestBody DeleteRequestDto deleteRequestDto) {
        var deletedRowsCount = patientFacade.deletePatientsLastVisitBetween(
                deleteRequestDto.getFrom(), deleteRequestDto.getTo());
        return new DeleteResultDto(deletedRowsCount);
    }

    @PostMapping("add-random-patients")
    public void generateRandomPatients(@RequestParam(value = "count", defaultValue = "1000") int count) {
        patientFacade.addRandomPatients(count);
    }
}
