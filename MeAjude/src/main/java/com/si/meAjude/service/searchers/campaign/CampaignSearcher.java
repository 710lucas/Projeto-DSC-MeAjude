package com.si.meAjude.service.searchers.campaign;

import com.si.meAjude.models.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignSearcher {

    Page<Campaign> search(Pageable pageable, CampaignSearchContent campaignSearchContent);

    CampaignSearchCriterion getCriterion();

}
