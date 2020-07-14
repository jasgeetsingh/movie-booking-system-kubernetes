package com.mbs.adminservice.repository;

import com.mbs.adminservice.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TheatreRepository extends JpaRepository<Theatre, Serializable> {

}
