package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.EntityType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"conteudo", "tipoEntidade"})
@ToString
public class Documento {

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "CPF_ou_CNPJ")
    private DocumentType documentType;

    @NotBlank
    @Column(name = "conteudo_documento", unique = true)
    private String conteudo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EntityType tipoEntidade;

    @Transient
    private DocumentValidator documentValidator;

    public void setAndValidateDocument(DocumentValidator documentValidator) {
        setDocumentValidator(documentValidator);
        validateDocument();
    }

    public void setDocumentValidator(DocumentValidator documentValidator) {
        this.documentValidator = documentValidator;
    }

    public void validateDocument() {
        documentValidator.validate(conteudo, tipoEntidade);
    }
}
