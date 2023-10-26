package com.si.meAjude.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.service.searchers.donation.dtos.DonationSearchContent;
import com.si.meAjude.service.DonationService;
import com.si.meAjude.service.dtos.donation.DonationDTO;
import com.si.meAjude.service.dtos.donation.DonationSaveDTO;
import com.si.meAjude.util.PageableUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "Donation", description = "Donation management APIs")
@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    DonationService donationService;


    @Operation(
            summary = "Create a New Donation",
            description = "Create a new donation by providing the required information in the request body. Returns the created DonationDTO with details such as id, donor information, donation amount, and other attributes.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DonationDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<DonationDTO> saveDonation(@RequestBody @Valid DonationSaveDTO dto, Authentication authentication) {
        User requestUser = (User) authentication.getPrincipal();
        if(!requestUser.getId().equals(dto.userId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(donationService.save(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve Donation by ID",
            description = "Get detailed information about a donation by specifying its unique identifier. Returns a DonationDTO containing donation details such as id, donor information, donation amount, and other attributes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DonationDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<DonationDTO> getById(@PathVariable Long id,  Authentication authentication){
        User requestUser = (User) authentication.getPrincipal();
        DonationDTO donationDTO = donationService.getById(id);
        if(requestUser.getRole() != UserRole.ADMIN) if(!donationDTO.userId().equals(requestUser.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(donationService.getById(id), HttpStatus.OK);
    }

        @Operation(
            summary = "Retrieve All Donations",
            description = "Get a list of all donations. Returns a Page of DonationDTOs containing donation details such as id, donor information, donation amount, and other attributes.")

    @GetMapping
    public ResponseEntity<Page<DonationDTO>> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "date") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "campaignId", required = false) Long campaignId,
            @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) @RequestParam(name = "date", required = false)LocalDate date){

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        DonationSearchContent searchContent = new DonationSearchContent(userId, campaignId, date, null);
        return new ResponseEntity<>(donationService.getAll(page, searchContent), HttpStatus.OK);
    }
}
