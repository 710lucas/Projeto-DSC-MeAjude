package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "CAMPANHAS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private boolean active;
    @NotNull
    @NotEmpty
    private String title;
    private String description;
    @Positive
    private BigDecimal goal;
    @Future
    private LocalDateTime finalDate;
    @FutureOrPresent
    private LocalDateTime startingDate = LocalDateTime.now().plusMinutes(1);
    @ManyToOne
    @NotNull
    private User creator;

    @OneToMany(mappedBy = "campaign")
    private List<Donation> donations = new ArrayList<>();

    private BigDecimal raisedMoney = BigDecimal.ZERO;
    @NotNull
    private boolean deleted;


    public void addDonation(Donation donation) throws DoacaoInvalidaException {
        if(donation == null)
            throw new DoacaoInvalidaException("The donation that was informed is invalid");
        donations.add(donation);
    }

    public Donation getDonation(Long id) throws DoacaoInvalidaException {
        for(Donation d : donations)
            if(d.getId() == id) return d;
        throw new DoacaoInvalidaException("The id " + id + " is invalid");
    }
}

