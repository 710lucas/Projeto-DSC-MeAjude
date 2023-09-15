package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"conteudo", "documentoEnum"})
@ToString
public class Documento {

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_do_documento")
    private DocumentoEnum documentoEnum;

    @NotBlank
    @Column(name = "conteudo_do_documento", length = 14)
    private String conteudo;
}
