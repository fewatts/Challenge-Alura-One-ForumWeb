package com.api.forumweb.app.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Curso;

/**
 * Repositório para entidade Curso. Realiza operações de persistência e consulta
 * no banco de dados para a entidade Curso.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    /**
     * Retorna uma página de cursos com base na configuração de paginação fornecida.
     *
     * @param paginacao Configuração de paginação para a consulta.
     * @return Uma página contendo os cursos encontrados.
     */
    Page<Curso> findAll(Pageable paginacao);

    /**
     * Verifica se existe um curso com o nome especificado, ignorando maiúsculas e
     * minúsculas.
     *
     * @param nome Nome do curso a ser verificado.
     * @return true se existe um curso com o nome especificado, caso contrário
     *         false.
     */
    boolean existsByNomeIgnoreCase(String nome);
}
