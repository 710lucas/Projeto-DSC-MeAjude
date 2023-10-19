package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"content", "documentEntityType"})
@ToString
public class Document {

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "CPF_or_CNPJ")
    private DocumentType documentType;

    @NotBlank
    @Column(name = "document_content", unique = true)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "individual_or_legalType")
    private DocumentEntityType documentEntityType;

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
        documentValidator.validate(content, documentEntityType);
    }
}
