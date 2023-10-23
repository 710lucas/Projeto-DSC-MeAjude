package com.si.meAjude.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name="donors")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(of = "id")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50)
    private String name;

    @NotBlank
    @Column(length = 13, unique = true)
    private String phone;

    private boolean deleted;

    @NotNull
    @Embedded
    private Document document;
}
