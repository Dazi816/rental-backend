package it.cgmconsulting.folino.repository;

import it.cgmconsulting.folino.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Set;

public interface StaffRepository extends JpaRepository<Staff, Long> {


    boolean existsByStaffIdIn(Set<Long> actorsIdCleaned);

    Set<Staff> findByStaffIdIn(Set<Long> actorsIdCleaned);

}
