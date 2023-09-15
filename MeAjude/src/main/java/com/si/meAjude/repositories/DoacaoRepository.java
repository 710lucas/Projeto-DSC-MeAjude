package com.si.meAjude.repositories;

import com.si.meAjude.models.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}
