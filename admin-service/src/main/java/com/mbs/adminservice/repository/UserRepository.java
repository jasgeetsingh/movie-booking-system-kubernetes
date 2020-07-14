package com.mbs.adminservice.repository;

import com.mbs.adminservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
