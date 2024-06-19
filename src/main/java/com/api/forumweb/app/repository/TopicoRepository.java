package com.api.forumweb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
    
}
