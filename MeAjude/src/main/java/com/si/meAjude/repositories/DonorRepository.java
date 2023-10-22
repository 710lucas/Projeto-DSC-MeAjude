package com.si.meAjude.repositories;

import com.si.meAjude.models.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    Page<Donor> findAll(Pageable pageable);
    Page<Donor> findAllByDeletedFalse(Pageable page);




}
