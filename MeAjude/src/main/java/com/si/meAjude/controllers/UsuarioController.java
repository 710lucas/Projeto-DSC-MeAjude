package com.si.meAjude.controllers;

import com.si.meAjude.service.UsuarioService;
import com.si.meAjude.service.dtos.usuario.UsuarioDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioSaveDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioUpdateDTO;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioSaveDTO usuario){
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioDTO> getAllByDeletadoFalse(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "nome") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection){
        return usuarioService.getAllByDeletedFalse(PageableUtil.getPageableWithSort(page, sortField, sortDirection));
    }

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "nome") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
    return usuarioService.getAll(PageableUtil.getPageableWithSort(page, sortField, sortDirection));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioUpdateDTO updateDTO){
        return new ResponseEntity<>(usuarioService.update(updateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO> logicDelete(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.logicDelete(id), HttpStatus.OK);
    }

    @DeleteMapping("/definitivo/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.delete(id), HttpStatus.OK);
    }
}
