package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Documento;
import com.si.meAjude.models.Usuario;
import lombok.Data;

@Data
public class UsuarioUpdateDTO{
    private Long id;
    private String email;
    private String nome;
    private String celular;
    private String senha;
//    private Documento documento;


    public UsuarioUpdateDTO() {
    }

    public UsuarioUpdateDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.celular = usuario.getCelular();
        this.senha = usuario.getSenha();
//        this.documento = usuario.getDocumento();
    }

    public Usuario updateUsuario(Usuario usuario){
        return updateUsuario(this, usuario);
    }

    public static Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO, Usuario usuario){
        if(usuarioUpdateDTO.getNome()!= null) usuario.setNome(usuarioUpdateDTO.getNome());
        if(usuarioUpdateDTO.getEmail()!= null) usuario.setEmail(usuarioUpdateDTO.getEmail());
        if(usuarioUpdateDTO.getCelular() != null) usuario.setCelular(usuarioUpdateDTO.getCelular());
//        if(usuarioUpdateDTO.getDocumento()!= null) usuario.setDocumento(usuarioUpdateDTO.getDocumento());
        if(usuarioUpdateDTO.getSenha() != null) usuario.setSenha(usuarioUpdateDTO.getSenha());
        return usuario;
    }
}