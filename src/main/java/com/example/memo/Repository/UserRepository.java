package com.example.memo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memo.Entity.User;

public interface UserRepository extends JpaRepository<User, String>{


    User findByUserId(String userId);

    User findByEmail(String email);
}
