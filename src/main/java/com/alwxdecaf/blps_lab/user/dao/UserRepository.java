package com.alwxdecaf.blps_lab.user.dao;

import com.alwxdecaf.blps_lab.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

