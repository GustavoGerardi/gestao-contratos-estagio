package com.example.demo.repositories;

import com.example.demo.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByEmail(String email);

    @Query(value = "SELECT tb_student.ra FROM tb_account INNER JOIN tb_student ON tb_account.id = tb_student.id ", nativeQuery = true)
    List<AccountEntity> findAllStudentRAs();

    @Query(value = "SELECT * FROM tb_account WHERE user_id = :userId", nativeQuery = true)
    AccountEntity findByUserId(Long userId);
}

