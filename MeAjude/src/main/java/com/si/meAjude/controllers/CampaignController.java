package com.si.meAjude.controllers;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.service.CampaignService;
import com.si.meAjude.service.dtos.campaign.CampaignDTO;
import com.si.meAjude.service.dtos.campaign.CampaignSaveDTO;
import com.si.meAjude.service.dtos.campaign.CampaignUpdateDTO;
import com.si.meAjude.service.searchers.campaign.CampaignSearchContent;
import com.si.meAjude.service.searchers.campaign.CampaignSearchCriterion;
import com.si.meAjude.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {


    @Autowired
    CampaignService campaignService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CampaignDTO> add(@RequestBody CampaignSaveDTO campaign, Authentication authentication) throws InvalidDateException, InvalidTitleException, InvalidCreatorException, InvalidDescriptionException, InvalidGoalException {
        User userFromRequest = (User) authentication.getPrincipal();
        if(!userFromRequest.getId().equals(campaign.creatorId())) return new ResponseEntity<CampaignDTO>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<CampaignDTO>(campaignService.save(campaign), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        CampaignDTO campaignDTO = campaignService.getCampaign(id);
        if(user.getRole() != UserRole.ADMIN && !campaignDTO.creatorId().equals(user.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(campaignDTO, HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CampaignDTO> getById(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "finalDate") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "desc") String sortDirection,
            @RequestParam(name = "criterion", required = false, defaultValue = "ACTIVE_DATE") CampaignSearchCriterion criterion,
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "goal", required = false) BigDecimal goal,
            @RequestParam(name = "active", required = false, defaultValue = "true") boolean active,
            @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) @RequestParam(name = "date", required = false) LocalDate initialDate){

        CampaignSearchContent searchContent = new CampaignSearchContent(criterion, userId, initialDate, active, goal);
        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return campaignService.getAll(page, searchContent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignDTO> update(@RequestBody CampaignUpdateDTO campaign) throws InvalidDateException, InvalidTitleException, InvalidDescriptionException, InvalidGoalException, InvalidCreatorException {
        return ResponseEntity.ok(campaignService.update(campaign));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CampaignDTO remove(@PathVariable Long id){
        return campaignService.logicRemoveCampaign(id);
    }
}
