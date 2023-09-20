package com.si.meAjude.models;


import com.si.meAjude.models.enums.EntidadeEnum;
import com.si.meAjude.service.dtos.UsuarioDTO;
import com.si.meAjude.service.dtos.UsuarioUpdateDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name="USUARIOS")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 50)
    private String nome;

    @Email
    @Column(unique = true)
    @NotNull
    private String email;

    @NotBlank
    @Column(length = 13)
    private String celular;

    @NotBlank
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EntidadeEnum tipoEntidade;

    private boolean deletado;

    @NotNull
    @Embedded
    private Documento documento;


    protected void setDelatado(boolean truOrFalse){
        this.deletado = truOrFalse;
    }

    public void delete(){
        setDelatado(true);
    }

    public void restaurar(){
        setDelatado(false);
    }
}
