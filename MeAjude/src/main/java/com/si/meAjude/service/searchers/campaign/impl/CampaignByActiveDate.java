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

@Component
public class CampaignByActiveDate implements CampaignSearcher {


    @Autowired
    private CampaignRepository campaignRepository;

    private Page<Campaign> searchByActiveDate(Pageable page, boolean active){
        return campaignRepository.findAllByActiveOrderByStartingDate(page, active);
    }

    @Override
    public Page<Campaign> search(Pageable pageable, CampaignSearchContent campaignSearchContent) {
        return searchByActiveDate(pageable, campaignSearchContent.active());
    }

    @Override
    public CampaignSearchCriterion getCriterion() {
        return CampaignSearchCriterion.ACTIVE_DATE;
    }
}
