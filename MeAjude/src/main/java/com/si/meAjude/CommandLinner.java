package com.si.meAjude;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Documento;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.EntityType;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public void run(String... args) throws Exception {

        Usuario usuarioTest = new Usuario();
        usuarioTest.setNome("Teste");
        usuarioTest.setEmail("teste@gmail.com");
        usuarioTest.setSenha("senhaTeste");
        usuarioTest.setCelular("989397659");
        usuarioTest.setDocumento(new Documento(DocumentType.CPF, "864.667.820-26", EntityType.PESSOA_FISICA, new CPFValidator()));

        Campanha campanha = new Campanha();
        campanha.setMeta(BigDecimal.valueOf(1000));
        campanha.setCriador(usuarioTest);
        campanha.setTitulo("Campanha Teste");
        campanha.setDescricao("Campanha de teste");
        campanha.setDataFinal(LocalDate.now().plus(1, ChronoUnit.DAYS).atStartOfDay());


        usuarioRepository.save(usuarioTest);
        campanhaRepository.save(campanha);
    }
}
