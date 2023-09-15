package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Documento {
    private DocumentoEnum documentoEnum;
    private String conteudo;
}
