package com.superdevs.hospitalmigration.ext;

import com.superdevs.hospitalmigration.patient.Patient;
import com.superdevs.hospitalmigration.patient.PatientFacade;
import com.superdevs.hospitalmigration.patient.repository.PatientRepository;
import com.superdevs.hospitalmigration.staff.StaffFacade;
import com.superdevs.hospitalmigration.staff.apimodel.AddStaffMemberDto;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.UUID;

import static com.superdevs.hospitalmigration.ext.TestContextHolder.getBean;

public class DomainHelpers {
    public static UUID createAStaffMember() {
        var staffFacade = getBean(StaffFacade.class);
        var randomJoe = new AddStaffMemberDto("Random Joe", LocalDate.now());
        var createdMember = staffFacade.addStaffMember(randomJoe);
        return createdMember.getUuid();
    }

    public static void createPatientWith(String lastVisitedDate) {
        var patientFacade = getBean(PatientRepository.class);
        var patient = Patient.builder()
                .name("Random name")
                .age(1)
                .lastVisitDate(LocalDate.parse(lastVisitedDate))
                .build();

        getBean(TransactionTemplate.class).execute(ignored -> patientFacade.save(patient));
    }
}
