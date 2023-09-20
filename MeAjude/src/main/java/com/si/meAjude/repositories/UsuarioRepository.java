package com.si.meAjude.repositories;

import com.si.meAjude.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository< Usuario,Long> {
    Page<Usuario> findAllByDeletadoFalse(Pageable page);

    Page<Usuario> findAll(Pageable pageable);
}
