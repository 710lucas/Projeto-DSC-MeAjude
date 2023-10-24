package com.si.meAjude.service;

import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.searchers.campaign.CampaignSearchContent;
import com.si.meAjude.models.searchers.donation.DonationSearchContent;
import com.si.meAjude.service.dtos.campanha.CampaignDTO;
import com.si.meAjude.service.dtos.campanha.CampaignUpdateDTO;
import com.si.meAjude.service.dtos.campanha.CampaignListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface CampaignService {

    CampaignDTO removeCampaign(long id);

    CampaignDTO update(CampaignUpdateDTO campaign) throws InvalidDateException, InvalidGoalException, InvalidDescriptionException, InvalidTitleException, InvalidCreatorException;

    CampaignDTO getCampaign(Long id);

    CampaignDTO changeState(boolean state, long id);

    CampaignDTO changeTitle(String title, long id) throws InvalidTitleException;

    CampaignDTO changeDescription(String description, long id) throws InvalidDescriptionException;

    CampaignDTO changeGoal(BigDecimal goal, long id) throws InvalidGoalException;

    CampaignDTO changeFinalDate(LocalDate finalDate, long id) throws InvalidDateException;
    CampaignDTO changeCreator(long creator_id, long id) throws InvalidCreatorException;

    CampaignDTO addCampaign(CampaignDTO dto) throws InvalidDateException, InvalidTitleException, InvalidCreatorException, InvalidDescriptionException, InvalidGoalException;

    Page<CampaignDTO> getAll(Pageable page, CampaignSearchContent searchcontent);


}
