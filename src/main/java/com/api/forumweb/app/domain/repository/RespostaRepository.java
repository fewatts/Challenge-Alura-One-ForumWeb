package com.api.forumweb.app.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Resposta;

/**
 * Repositório para entidade Resposta. Realiza operações de persistência e
 * consulta no banco de dados para a entidade Resposta.
 */
@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    /**
     * Retorna uma página de respostas com base na configuração de paginação
     * fornecida.
     *
     * @param paginacao Configuração de paginação para a consulta.
     * @return Uma página contendo as respostas encontradas.
     */
    Page<Resposta> findAll(Pageable paginacao);
}
