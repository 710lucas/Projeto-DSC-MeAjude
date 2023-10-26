package com.si.meAjude.service.searchers.campaign.impl;

import com.si.meAjude.models.Campaign;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.service.searchers.campaign.CampaignSearchContent;
import com.si.meAjude.service.searchers.campaign.CampaignSearchCriterion;
import com.si.meAjude.service.searchers.campaign.CampaignSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CampaignByEndsBeforeService implements CampaignSearcher {

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
