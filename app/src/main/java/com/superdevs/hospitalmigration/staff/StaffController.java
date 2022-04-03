package com.superdevs.hospitalmigration.staff;

import com.superdevs.hospitalmigration.staff.apimodel.AddStaffMemberDto;
import com.superdevs.hospitalmigration.staff.apimodel.StaffMemberDto;
import com.superdevs.hospitalmigration.staff.apimodel.UpdateStaffMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StaffController {
    private final StaffFacade staffFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/staff-members", produces = "application/json")
    public StaffMemberDto addStaffMember(@Validated @RequestBody AddStaffMemberDto addStaffMemberDto) {
        return staffFacade.addStaffMember(addStaffMemberDto);
    }

    @PutMapping(value = "/staff-members/{id}", produces = "application/json")
    public StaffMemberDto updateStaffMember(@PathVariable Long id, @Validated @RequestBody UpdateStaffMemberDto updateStaffMemberDto) {
        return staffFacade.updateStaffMember(id, updateStaffMemberDto);
    }
}
