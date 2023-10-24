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
