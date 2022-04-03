package com.superdevs.hospitalmigration.patient;

import com.github.javafaker.Faker;
import com.superdevs.hospitalmigration.ext.NotFoundException;
import com.superdevs.hospitalmigration.patient.apimodel.PatientDto;
import com.superdevs.hospitalmigration.patient.repository.PatientRepository;
import com.superdevs.hospitalmigration.patient.service.PatientCsvMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientFacade {
    private final PatientRepository patientRepository;

    public List<PatientDto> fetchAllPatientsUpToTwoYearsOld() {
        return patientRepository.selectAllUpToYearsOld(2).stream()
                .map(it -> new PatientDto(it.getId(), it.getName(), it.getAge(), it.getLastVisitDate()))
                .collect(Collectors.toList());
    }

    // this is byte[] array ONLY because we are dealing with single patient! Otherwise it should be buffered.
    public byte[] getPatientAsCSV(Long patientId) {
        var patient = patientRepository.findById(patientId).orElseThrow(NotFoundException::new);
        return new PatientCsvMapper().toCSVByteArray(patient);
    }

    @Transactional
    public int deletePatientsLastVisitBetween(LocalDate from, LocalDate to) {
        return patientRepository.deletePatientsLastVisitedBetween(from, to);
    }

    public void addRandomPatients(int count) {
        var faker = new Faker();
        for (int i = 0; i < count; i++) {
            var randomLastVisitDate = faker.date().past(5 * 365, TimeUnit.DAYS)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            var patient = Patient.builder()
                    .name(faker.name().name())
                    .age(faker.number().numberBetween(0, 120))
                    .lastVisitDate(randomLastVisitDate)
                    .build();
            patientRepository.save(patient);
        }
    }
}
