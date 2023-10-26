package com.si.meAjude.service;

import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.dtos.campaign.CampaignDTO;
import com.si.meAjude.service.dtos.campaign.CampaignSaveDTO;
import com.si.meAjude.service.dtos.campaign.CampaignUpdateDTO;
import com.si.meAjude.service.searchers.campaign.CampaignSearchContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CampaignService {

    CampaignDTO save(CampaignSaveDTO dto) throws InvalidDateException, InvalidTitleException, InvalidCreatorException, InvalidDescriptionException, InvalidGoalException;

    CampaignDTO getCampaign(Long id);
    Page<CampaignDTO> getAll(Pageable page, CampaignSearchContent searchcontent);

    CampaignDTO logicRemoveCampaign(long id);

    CampaignDTO update(CampaignUpdateDTO campaign) throws InvalidDateException, InvalidGoalException, InvalidDescriptionException, InvalidTitleException, InvalidCreatorException;


}
