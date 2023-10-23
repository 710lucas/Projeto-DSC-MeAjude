package com.si.meAjude.repositories;

import com.si.meAjude.models.campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<campaign, Long> {
}
