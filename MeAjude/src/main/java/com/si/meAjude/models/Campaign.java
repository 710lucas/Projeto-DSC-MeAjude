package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "campaigns")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private boolean active;
    @NotNull
    @NotBlank
    @Column(length = 50)
    private String title;

    @NotNull
    @NotBlank
    @Column(length = 50)
    private String description;

    @Positive
    private BigDecimal goal;

    @Future
    private LocalDate finalDate;

    @FutureOrPresent
    private LocalDate startingDate = LocalDate.now();

    @ManyToOne
    @NotNull
    private User creator;

    @OneToMany(mappedBy = "campaign")
    private List<Donation> donations = new ArrayList<>();

    @Transient
    private BigDecimal raisedMoney = BigDecimal.ZERO;

    @NotNull
    private boolean deleted;


    public void addDonation(Donation donation) throws InvalidDonationException {
        if(donation == null)
            throw new InvalidDonationException("The donation that was informed is invalid");
        donations.add(donation);
    }

    public Donation getDonation(Long id) throws InvalidDonationException {
        for(Donation d : donations)
            if(d.getId() == id) return d;
        throw new InvalidDonationException("The id " + id + " is invalid");
    }

    public BigDecimal getRaisedMoney() {
        BigDecimal sum = BigDecimal.ZERO;
        for(Donation d : donations)
            sum = sum.add(d.getDonationValue());
        return sum;
    }
}

