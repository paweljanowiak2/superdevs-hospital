package com.superdevs.hospitalmigration.patient.repository;

import com.superdevs.hospitalmigration.patient.Patient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Query("select p from Patient p where p.age < :ageUpperBoundary")
    List<Patient> selectAllUpToYearsOld(@Param("ageUpperBoundary") int ageUpperBoundary);

    @Modifying
    @Query("delete from Patient p where p.lastVisitDate >= :from and p.lastVisitDate <= :to")
    int deletePatientsLastVisitedBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
