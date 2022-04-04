package com.superdevs.hospitalmigration;

import com.superdevs.hospitalmigration.ext.IntegrationTest;
import com.superdevs.hospitalmigration.staff.apimodel.AddStaffMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;

import static com.superdevs.hospitalmigration.ext.AppExtMatchers.isAnUUID;
import static com.superdevs.hospitalmigration.ext.DomainHelpers.createAStaffMember;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@IntegrationTest
public class StaffMemberCreationSecurityTest {
    @Test
    void should_create_a_staff_member_when_no_staff_member_exists() {
        given()
                .header("x-staff-uuid", App.INITIAL_SECURITY_KEY)
                .body(newStaffMemberDto())
                .contentType("application/json")
                .post("/api/staff-members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", is(1))
                .body("uuid", isAnUUID())
                .body("name", equalTo("JohnDoe"))
                .body("registrationDate", equalTo("2020-02-02"));

        // here, depends on the convention we can add additional request to the backend to figure out
        // if staff member is actually created (BDD approach). I assumed that assigning ids is sufficient for this.
    }

    @Test
    void should_add_staff_member_using_recently_created_staffs_member_uuid() {
        var staffUUID = createAStaffMember();

        given()
                .header("x-staff-uuid", staffUUID)
                .body(newStaffMemberDto())
                .contentType("application/json")
                .post("/api/staff-members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void should_reject_add_staff_member_when_random_key_provided() {
        given()
                .header("x-staff-uuid", UUID.randomUUID().toString())
                .body(newStaffMemberDto())
                .contentType("application/json")
                .post("/api/staff-members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    private AddStaffMemberDto newStaffMemberDto() {
        return new AddStaffMemberDto("JohnDoe", LocalDate.parse("2020-02-02"));
    }
}
