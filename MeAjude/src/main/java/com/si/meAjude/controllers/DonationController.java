package com.si.meAjude.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.models.searchers.donation.DonationSearchContent;
import com.si.meAjude.models.searchers.donation.DonationSearchCriterion;
import com.si.meAjude.service.DonationService;
import com.si.meAjude.service.dtos.doacao.DonationDTO;
import com.si.meAjude.service.dtos.doacao.DonationSaveDTO;
import com.si.meAjude.util.PageableUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    DonationService donationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DonationDTO saveDoacao(@RequestBody @Valid DonationSaveDTO dto) {
        return donationService.save(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DonationDTO getById(@PathVariable Long id) {
        return donationService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DonationDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "date") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "criterion", required = false, defaultValue = "ALL") DonationSearchCriterion criterion,
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "campaignId", required = false) Long campaignId,
            @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) @RequestParam(name = "date", required = false)LocalDate date){

        DonationSearchContent searchContent = new DonationSearchContent(criterion, userId, campaignId, date, null);
        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);

        return donationService.getAll(page, searchContent);
    }
}
