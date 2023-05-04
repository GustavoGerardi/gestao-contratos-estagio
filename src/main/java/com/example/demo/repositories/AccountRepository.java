package com.example.demo.repositories;

import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByEmail(String email);

    @Query(value = "SELECT * FROM tb_account WHERE user_id = :userId", nativeQuery = true)
    AccountEntity findByUserId(Long userId);
}

