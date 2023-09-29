package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.interfaces.DocumentValidator;
import jakarta.persistence.*;
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
    private DocumentType documentType;

    @NotBlank
    @Column(name = "conteudo_do_documento", length = 14)
    private String conteudo;

    @Transient
    private DocumentValidator documentValidator;

    public void setDocumentValidator(DocumentValidator documentValidator) {
        this.documentValidator = documentValidator;
        documentValidator.validate(conteudo);
    }
}
