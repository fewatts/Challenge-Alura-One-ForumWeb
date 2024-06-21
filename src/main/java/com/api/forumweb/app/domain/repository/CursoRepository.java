package com.api.forumweb.app.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

    Page<Curso> findAll(Pageable paginacao);

    boolean existsByNomeIgnoreCase(String nome);
}
