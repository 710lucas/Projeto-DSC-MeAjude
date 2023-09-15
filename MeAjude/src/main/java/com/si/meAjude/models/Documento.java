package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentoEnum;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Documento {
    private DocumentoEnum documentoEnum;
    private String conteudo;
}
