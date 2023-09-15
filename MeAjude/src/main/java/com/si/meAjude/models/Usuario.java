package com.si.meAjude.models;


import com.si.meAjude.models.enums.ClasseEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name="usuarios")
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
    private String email;

    @NotBlank
    @Column(length = 13)
    private String celular;

    @NotNull
    @Embedded
    private Documento documento;

    @NotNull
    private ClasseEnum classe;

}
