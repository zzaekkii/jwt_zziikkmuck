package com.example.yoomeejwt.domains.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositrory extends JpaRepository<User, Long> {
}
