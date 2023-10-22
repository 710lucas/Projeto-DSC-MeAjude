package com.si.meAjude.controllers;

import com.si.meAjude.service.DonorSerivce;
import com.si.meAjude.service.dtos.donor.DonorDTO;
import com.si.meAjude.service.dtos.donor.DonorSaveDTO;
import com.si.meAjude.service.dtos.donor.DonorUpdateDTO;
import com.si.meAjude.util.PageableUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/donors")
public class DonorsController {

    @Autowired
    DonorSerivce donorSerivce;

    @PostMapping
    public ResponseEntity<DonorDTO> save(@RequestBody @Valid DonorSaveDTO usuario){
        return new ResponseEntity<>(donorSerivce.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonorDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(donorSerivce.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<DonorDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "name") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
    return donorSerivce.getAll(PageableUtil.getPageableWithSort(page, sortField, sortDirection));
    }

    @PutMapping
    public ResponseEntity<DonorDTO> update(@RequestBody @Valid DonorUpdateDTO updateDTO){
        return new ResponseEntity<>(donorSerivce.update(updateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DonorDTO> logicDelete(@PathVariable Long id){
        return new ResponseEntity<>(donorSerivce.logicDelete(id), HttpStatus.OK);
    }

}
