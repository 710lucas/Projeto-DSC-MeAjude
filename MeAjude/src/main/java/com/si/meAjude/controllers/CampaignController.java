package com.si.meAjude.controllers;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.CampaignService;
import com.si.meAjude.service.dtos.campanha.CampaignDTO;
import com.si.meAjude.service.dtos.campanha.CampaignUpdateDTO;
import com.si.meAjude.service.dtos.campanha.CampaignListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/campanhas")
public class CampaignController {


    @Autowired
    CampaignService campaignService;


    @PostMapping
    public ResponseEntity<CampaignDTO> add(@RequestBody CampaignDTO campaign) throws InvalidDateException, InvalidTitleException, InvalidCreatorException, InvalidDescriptionException, InvalidGoalException {
        return ResponseEntity.ok(campaignService.addCampaign(campaign));
    }

    @DeleteMapping
    public ResponseEntity<CampaignDTO> remove(@RequestBody CampaignUpdateDTO campaign){
        return ResponseEntity.ok(campaignService.removeCampaign(campaign.id()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable String id){
        return ResponseEntity.ok(campaignService.getCampaign(Long.parseLong(id)));
    }

    @PutMapping()
    public ResponseEntity<CampaignDTO> modificar(@RequestBody CampaignUpdateDTO campaign) throws InvalidDateException, InvalidTitleException, InvalidDescriptionException, InvalidGoalException, InvalidCreatorException {
        return ResponseEntity.ok(campaignService.update(campaign));
    }


    @GetMapping()
    public ResponseEntity<CampaignListDTO> listar(@RequestParam(name = "amount", required = false, defaultValue = "-1") String amount, @RequestParam(name = "filter", required = false) String filter) throws  CriterioInvalidoException {
        return ResponseEntity.ok(campaignService.listCampaign(Optional.of(Long.parseLong(amount)), Optional.ofNullable(filter)));
    }




}
