package com.superdevs.hospitalmigration.staff;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StaffMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type="uuid-char")
    private UUID uuid;

    @Setter
    private String name;

    @Setter
    private LocalDate registrationDate;
}
