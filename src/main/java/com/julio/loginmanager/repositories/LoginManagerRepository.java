package com.julio.loginmanager.repositories;

import com.julio.loginmanager.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginManagerRepository extends JpaRepository<LoginModel, UUID> {
    LoginModel findByLoginId(UUID loginId);
}
