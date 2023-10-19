package com.si.meAjude.repositories;

import com.si.meAjude.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findAll(Pageable pageable);
    Page<User> findAllByDeletedFalse(Pageable page);
}
