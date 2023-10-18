package com.si.meAjude.models;


import com.si.meAjude.models.enums.EntityType;
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
    @NotBlank
    private String email;

    @NotBlank
    @Column(length = 13, unique = true)
    private String celular;

    @NotBlank
    private String senha;

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
