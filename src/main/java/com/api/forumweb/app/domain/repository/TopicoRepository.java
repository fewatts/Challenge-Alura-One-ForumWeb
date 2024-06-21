package com.api.forumweb.app.domain.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
    
    boolean existsByTituloIgnoreCase(String titulo);

    boolean existsByMensagemIgnoreCase(String mensagem);

    Page<Topico> findAll(Pageable paginacao);

    boolean findByCursoId(Long id); 

}
