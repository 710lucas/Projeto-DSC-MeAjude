package com.si.meAjude;

import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.Document;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Configuration
@Profile("h2")
public class CommandLinner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public void run(String... args) throws Exception {

        User userTest = new User();
        userTest.setName("Teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setPassword("senhaTeste");
        userTest.setPhone("989397659");
        userTest.setDocument(new Document(DocumentType.CPF, "864.667.820-26", DocumentEntityType.INDIVIDUAL, new CPFValidator()));

        Campaign campaign = new Campaign();
        campaign.setMeta(BigDecimal.valueOf(1000));
        campaign.setCriador(userTest);
        campaign.setTitulo("Campanha Teste");
        campaign.setDescricao("Campanha de teste");
        campaign.setDataFinal(LocalDate.now().plus(1, ChronoUnit.DAYS).atStartOfDay());


        userRepository.save(userTest);
        campaignRepository.save(campaign);
    }
}
