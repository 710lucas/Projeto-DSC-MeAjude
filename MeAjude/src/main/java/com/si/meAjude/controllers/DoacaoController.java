package com.si.meAjude.controllers;

import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoSaveDTO;
import com.si.meAjude.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    @Autowired
    DoacaoService doacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoacaoDTO saveDoacao(@RequestBody DoacaoSaveDTO dto) {
        return doacaoService.save(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoacaoDTO getById(@PathVariable Long id) {
        return doacaoService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getAll(page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoacaoDTO logicDelete(@PathVariable Long id) {
        return doacaoService.logicDelete(id);
    }
}
