package com.si.meAjude.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 50)
    private String name;

    @Email
    @Column(unique = true, length = 50)
    @NotBlank
    private String email;

    @NotBlank
    @Column(length = 13, unique = true)
    private String phone;

    @NotBlank
    private String password;

    private boolean deleted;

    @NotNull
    @Embedded
    private Document document;
}
