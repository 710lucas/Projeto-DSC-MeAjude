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

import java.math.BigDecimal;

@Component
public class CampaignByGoalReached implements CampaignSearcher {

    @Autowired
    private CampaignRepository campaignRepository;

    private Page<Campaign> searchByGoalReached(Pageable page, BigDecimal value, boolean active){
        return campaignRepository.findAllByRaisedMoneyGreaterThanAndActive(page, value, active);
    }

    @Override
    public Page<Campaign> search(Pageable pageable, CampaignSearchContent campaignSearchContent) {
        return searchByGoalReached(pageable,campaignSearchContent.goal(), campaignSearchContent.active());
    }

    @Override
    public CampaignSearchCriterion getCriterion() {
        return CampaignSearchCriterion.GOAL_REACHED;
    }
}
