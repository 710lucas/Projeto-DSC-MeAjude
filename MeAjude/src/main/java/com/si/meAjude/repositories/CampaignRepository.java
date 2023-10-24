package com.si.meAjude.repositories;

import com.si.meAjude.models.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Page<Campaign> findAllByActiveOrderByTitleAsc(Pageable page, boolean active);

    Page<Campaign> findAllByFinalDateBeforeAndActive(Pageable page, LocalDate finalDate, boolean active);

    Page<Campaign> findAllByActiveOrderByStartingDate(Pageable page, boolean active);

    Page<Campaign> findAllByRaisedMoneyGreaterThanAndActive(Pageable page, BigDecimal meta, boolean active);

}
