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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Tag(name = "Campaign", description = "Campaign management APIs")
@RestController
@RequestMapping("/campaigns")
public class CampaignController {


    @Autowired
    CampaignService campaignService;


    @Operation(
            summary = "Create a New Campaign",
            description = "Create a new campaign by providing the required information in the request body. Returns the created CampaignDTO with details such as id, title, description, and goal.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = CampaignDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CampaignDTO> add(@RequestBody CampaignSaveDTO campaign, Authentication authentication) throws InvalidDateException, InvalidTitleException, InvalidCreatorException, InvalidDescriptionException, InvalidGoalException {
        User userFromRequest = (User) authentication.getPrincipal();
        if(!userFromRequest.getId().equals(campaign.creatorId())) return new ResponseEntity<CampaignDTO>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<CampaignDTO>(campaignService.save(campaign), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve Campaigns",
            description = "Get a paginated list of campaigns with optional filtering and sorting parameters. Returns a Page of CampaignDTOs with details such as id, title, description, and goal.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = CampaignDTO.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable Long id){
        CampaignDTO campaignDTO = campaignService.getCampaign(id);
        return new ResponseEntity<>(campaignDTO, HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CampaignDTO> getAll(
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

    @Operation(
            summary = "Update Campaign",
            description = "Update a campaign by providing the required information in the request body. Returns the updated CampaignDTO with details such as id, title, description, and goal.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CampaignDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })


    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody CampaignUpdateDTO campaign, @PathVariable Long id) throws InvalidDateException, InvalidTitleException, InvalidDescriptionException, InvalidGoalException, InvalidCreatorException {
        User userFromRequest = getUserFromRequest();
        CampaignDTO campaignDTO = campaignService.getCampaign(id);
        if(userFromRequest.getRole() != UserRole.ADMIN && userFromRequest.getId() != campaignDTO.creatorId()) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        campaignService.update(campaign, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Retrieve Campaign by ID",
            description = "Get detailed information about a campaign by specifying its unique identifier. Returns a CampaignDTO containing campaign details such as id, title, description, and goal.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CampaignDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id){
        User userFromRequest = getUserFromRequest();
        CampaignDTO campaignDTO = campaignService.getCampaign(id);
        if(userFromRequest.getRole() != UserRole.ADMIN && userFromRequest.getId() != campaignDTO.creatorId()) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        campaignService.logicRemoveCampaign(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    private User getUserFromRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}
