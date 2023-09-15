package com.si.meAjude.models;


import com.si.meAjude.models.enums.ClasseEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    private String celular;

    @NotNull
    @Embedded
    private Documento documento;

    @NotNull
    private ClasseEnum classe;

}
