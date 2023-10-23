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
import java.time.LocalDateTime;

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
        userTest.setDeleted(false);
        userTest.setDocument(new Document(DocumentType.CPF, "864.667.820-26", DocumentEntityType.INDIVIDUAL, new CPFValidator()));

        Campaign campaign = new Campaign();
        campaign.setGoal(BigDecimal.valueOf(1000));
        campaign.setCreator(userTest);
        campaign.setTitle("Campanha Teste");
        campaign.setDescription("Campanha de teste");
        campaign.setDeleted(false);
        campaign.setFinalDate(LocalDateTime.now().plusDays(2));

        userRepository.save(userTest);
        campaignRepository.save(campaign);
    }
}
