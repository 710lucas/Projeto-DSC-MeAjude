package com.si.meAjude.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "DOACOES")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "campanha_id")
    @NotNull
    @JsonBackReference
    private Campanha campanha;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull
    private BigDecimal valorDoado;

    private boolean deletado;

    private void setDeletado(boolean deletado){
        this.deletado = deletado;
    }

    public void delete(){
        this.deletado = true;
    }

    public void backUp(){
        this.deletado = false;
    }
}
