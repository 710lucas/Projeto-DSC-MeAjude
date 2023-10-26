package com.si.meAjude.repositories;

import com.si.meAjude.models.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    Page<Donation> findAll(Pageable page);

    Page<Donation> findAllByDate(Pageable page, LocalDate date);

    Page<Donation> findAllByUserId(Pageable pageable, Long userId);

    Page<Donation> findAllByCampaignId(Pageable page, Long id);

    Page<Donation> findAllByUserIdAndCampaignId(Pageable page, Long userId, Long campanhaId);

    Page<Donation> findAllByDateAndUserId(Pageable pageable, LocalDate data, Long userId);

    Page<Donation> findAllByDateAndCampaignId(Pageable page, LocalDate date, Long id);

    Page<Donation> findAllByDateAndUserIdAndCampaignId(Pageable page, LocalDate date, Long userId, Long campaignId);
}
