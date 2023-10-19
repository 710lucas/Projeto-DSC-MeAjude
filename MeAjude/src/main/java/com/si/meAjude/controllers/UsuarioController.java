package com.si.meAjude.controllers;

import com.si.meAjude.service.UserSerivce;
import com.si.meAjude.service.dtos.usuario.UserDTO;
import com.si.meAjude.service.dtos.usuario.UserSaveDTO;
import com.si.meAjude.service.dtos.usuario.UserUpdateDTO;
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
    UserSerivce userSerivce;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserSaveDTO usuario){
        return new ResponseEntity<>(userSerivce.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(userSerivce.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "name") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
    return userSerivce.getAll(PageableUtil.getPageableWithSort(page, sortField, sortDirection));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserUpdateDTO updateDTO){
        return new ResponseEntity<>(userSerivce.update(updateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> logicDelete(@PathVariable Long id){
        return new ResponseEntity<>(userSerivce.logicDelete(id), HttpStatus.OK);
    }

}
