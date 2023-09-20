package com.si.meAjude.controllers;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.UsuarioService;
import com.si.meAjude.service.dtos.UsuarioDTO;
import com.si.meAjude.service.dtos.UsuarioUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> getAllByDeletedFalse(){
        return new ResponseEntity<>(usuarioService.getAllByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioUpdateDTO updateDTO){
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
