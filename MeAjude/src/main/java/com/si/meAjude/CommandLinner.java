package com.si.meAjude;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Document;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Configuration
@Profile("h2")
public class CommandLinner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampanhaRepository campaignRepository;

    @Override
    public void run(String... args) throws Exception {

        User userTest = new User();
        userTest.setName("Teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setPassword("senhaTeste");
        userTest.setPhone("989397659");
        userTest.setDeleted(false);
        userTest.setDocument(new Document(DocumentType.CPF, "864.667.820-26", DocumentEntityType.INDIVIDUAL, new CPFValidator()));

        Campanha campaign = new Campanha();
        campaign.setMeta(BigDecimal.valueOf(1000));
        campaign.setCriador(userTest);
        campaign.setTitulo("Campanha Teste");
        campaign.setDescricao("Campanha de teste");
        campaign.setDeletado(false);
        campaign.setDataFinal(LocalDateTime.now().plusDays(2));

        userRepository.save(userTest);
        campaignRepository.save(campaign);
    }
}
