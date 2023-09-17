package com.si.meAjude.repositories;

import com.si.meAjude.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository< Usuario,Long> {
}
