package com.example.memo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.memo.Entity.VerificationToken;

import jakarta.transaction.Transactional;


@Repository

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer>{

    VerificationToken findByToken(String token);

    VerificationToken findByUser_UserId(String id);
    @Transactional
    @Modifying
    @Query("DELETE FROM VerificationToken v WHERE v.token =:token")
    void deleteByToken(@Param("token") String token);



}
