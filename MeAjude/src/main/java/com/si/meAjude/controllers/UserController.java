package com.si.meAjude.controllers;

import com.si.meAjude.service.UserSerivce;
import com.si.meAjude.service.dtos.user.UserDTO;
import com.si.meAjude.service.dtos.user.UserSaveDTO;
import com.si.meAjude.service.dtos.user.UserUpdateDTO;
import com.si.meAjude.util.PageableUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserSerivce userService;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserSaveDTO dto){
        return new ResponseEntity<>(userService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "name") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
    return userService.getAll(PageableUtil.getPageableWithSort(page, sortField, sortDirection));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserUpdateDTO updateDTO, Authentication authentication){
        if(!userService.canAccessUser(authentication, updateDTO.id())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(userService.update(updateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> logicDelete(@PathVariable Long id, Authentication authentication){
        if(!userService.canAccessUser(authentication, id)) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(userService.logicDelete(id), HttpStatus.OK);
    }
}
