package com.superdevs.hospitalmigration.staff;

import com.superdevs.hospitalmigration.ext.NotFoundException;
import com.superdevs.hospitalmigration.staff.apimodel.AddStaffMemberDto;
import com.superdevs.hospitalmigration.staff.apimodel.StaffMemberDto;
import com.superdevs.hospitalmigration.staff.apimodel.UpdateStaffMemberDto;
import com.superdevs.hospitalmigration.staff.repository.StaffMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffFacade {
    private final StaffMemberRepository staffMemberRepository;

    @Transactional
    public StaffMemberDto addStaffMember(AddStaffMemberDto addStaffMemberDto) {
        var staffMember = StaffMember.builder()
                .uuid(UUID.randomUUID())
                .name(addStaffMemberDto.getName())
                .registrationDate(addStaffMemberDto.getRegistrationDate())
                .build();

        staffMemberRepository.save(staffMember);

        return toStaffMemberDto(staffMember);
    }

    @Transactional
    public StaffMemberDto updateStaffMember(Long id, UpdateStaffMemberDto updateStaffMemberDto) {
        var staffMember = staffMemberRepository.findById(id).orElseThrow(NotFoundException::new);
        staffMember.setName(updateStaffMemberDto.getName());
        staffMember.setRegistrationDate(updateStaffMemberDto.getRegistrationDate());
        return toStaffMemberDto(staffMember);
    }

    private StaffMemberDto toStaffMemberDto(StaffMember staffMember) {
        return new StaffMemberDto(
                staffMember.getId(),
                staffMember.getUuid(),
                staffMember.getName(),
                staffMember.getRegistrationDate()
        );
    }

    public boolean isStaffUuidValid(UUID uuid) {
        return staffMemberRepository.findByUuid(uuid).isPresent();
    }

    public boolean isAnyStaffAvailable() {
        return staffMemberRepository.count() > 0;
    }
}
