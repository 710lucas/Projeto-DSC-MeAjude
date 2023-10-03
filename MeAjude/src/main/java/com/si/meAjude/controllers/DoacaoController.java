package com.si.meAjude.controllers;

import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoSaveDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoUpdateDTO;
import com.si.meAjude.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public DoacaoDTO updateDoacao(@RequestBody DoacaoUpdateDTO dto) {
        return doacaoService.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoacaoDTO logicDelete(@PathVariable Long id) {
        return doacaoService.delete(id);
    }

    @DeleteMapping("/definitivo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoacaoDTO delete(@PathVariable Long id) {
        return doacaoService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoacaoDTO getById(@PathVariable Long id) {
        return doacaoService.getById(id);
    }

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getAll(page);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getAllByDeletadoFalse(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getAllByDeletadoFalse(page);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByUserId(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByUserId(page, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByData(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "data")  @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByData(page, data);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByCampanhaId(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByCampanhaId(page, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByDataAndUserId(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "data")  @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByDataAndUserId(page, data, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByDataAndCampanhaId(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "data")  @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data,
            @RequestParam(name = "id") Long id) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByDataAndCampanhaId(page, data, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByDataAndUserIdAndCampanhaId(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "data")  @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data,
            @RequestParam(name = "usuarioId") Long usuarioId,
            @RequestParam(name = "campanhaId") Long campanhaId) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByDataAndUserIdAndCampanhaId(page, data, usuarioId, campanhaId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoacaoDTO> getByUsuarioAndCampanhaId(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "data") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "usuarioId") Long usuarioId, @RequestParam(name = "campanhaId") Long campanhaId) {

        page = PageableUtil.getPageableWithSort(page, sortField, sortDirection);
        return doacaoService.getByUsuarioIdAndCampanhaId(page, usuarioId, campanhaId);
    }
}
