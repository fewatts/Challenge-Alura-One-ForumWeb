package com.api.forumweb.app.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{
    Page<Resposta> findAll(Pageable paginacao);
}
