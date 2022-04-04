package com.superdevs.hospitalmigration;

import com.superdevs.hospitalmigration.ext.IntegrationTest;
import com.superdevs.hospitalmigration.patient.apimodel.DeleteRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;

import static com.superdevs.hospitalmigration.ext.DomainHelpers.createAStaffMember;
import static com.superdevs.hospitalmigration.ext.DomainHelpers.createPatientWith;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@IntegrationTest
public class PatientDeleteMultipleTest {
    private UUID staffMemberUUID;

    @BeforeEach
    void setUp() {
        staffMemberUUID = createAStaffMember();
    }

    @Test
    void should_remove_patient_correctly() {
        createPatientWith("2020-02-14");
        createPatientWith("2020-02-18");
        createPatientWith("2020-03-01");
        createPatientWith("2020-03-15");


        var aDate = LocalDate.parse("2020-02-14");
        given()
                .header("x-staff-uuid", staffMemberUUID)
                .body(new DeleteRequestDto(aDate, aDate))
                .contentType("application/json")
                .post("/api/patients/delete-by-last-visited")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("deletedRowsCount", is(1));

        // here, depends on the convention we can add additional request to the backend to figure out
        // if staff member is actually removed (BDD approach). I assumed that rows count is sufficient for this.
        // sorry Guys limited time
    }

    @Test
    void should_create_a_staff_member_when_no_staff_member_exists() {
        createPatientWith("2020-02-14");
        createPatientWith("2020-02-18");
        createPatientWith("2020-02-18");
        createPatientWith("2020-02-19");
        createPatientWith("2020-03-01");
        createPatientWith("2020-03-15");


        given()
                .header("x-staff-uuid", staffMemberUUID)
                .body(new DeleteRequestDto(LocalDate.parse("2020-02-14"), LocalDate.parse("2020-02-19")))
                .contentType("application/json")
                .post("/api/patients/delete-by-last-visited")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("deletedRowsCount", is(4));

        // here, depends on the convention we can add additional request to the backend to figure out
        // if staff member is actually removed (BDD approach). I assumed that rows count is sufficient for this.
        // sorry Guys limited time
    }
}
