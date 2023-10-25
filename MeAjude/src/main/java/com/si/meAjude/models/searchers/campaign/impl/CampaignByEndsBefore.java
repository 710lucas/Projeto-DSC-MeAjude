package com.si.meAjude.models.searchers.campaign.impl;

import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.searchers.campaign.CampaignSearchContent;
import com.si.meAjude.models.searchers.campaign.CampaignSearchCriterion;
import com.si.meAjude.models.searchers.campaign.CampaignSearcher;
import com.si.meAjude.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CampaignByEndsBefore implements CampaignSearcher {

    @Autowired
    private CampaignRepository campaignRepository;

    private Page<Campaign> searchByEndsBefore(Pageable page, LocalDate date, boolean active){
        return campaignRepository.findAllByFinalDateBeforeAndActive(page, date, active);
    }

    @Override
    public Page<Campaign> search(Pageable pageable, CampaignSearchContent campaignSearchContent) {
        return searchByEndsBefore(pageable, campaignSearchContent.finalDate(), campaignSearchContent.active());
    }

    @Override
    public CampaignSearchCriterion getCriterion() {
        return CampaignSearchCriterion.ENDS_BEFORE;
    }
}
