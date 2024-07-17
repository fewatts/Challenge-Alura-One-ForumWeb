package com.api.forumweb.app.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Topico;

/**
 * Repositório para entidade Topico. Realiza operações de persistência e
 * consulta no banco de dados para a entidade Topico.
 */
@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    /**
     * Verifica se existe um tópico com o título especificado, ignorando maiúsculas
     * e minúsculas.
     *
     * @param titulo Título do tópico a ser verificado.
     * @return Verdadeiro se existe um tópico com o título especificado, falso caso
     *         contrário.
     */
    boolean existsByTituloIgnoreCase(String titulo);

    /**
     * Verifica se existe um tópico com a mensagem especificada, ignorando
     * maiúsculas e minúsculas.
     *
     * @param mensagem Mensagem do tópico a ser verificado.
     * @return Verdadeiro se existe um tópico com a mensagem especificada, falso
     *         caso contrário.
     */
    boolean existsByMensagemIgnoreCase(String mensagem);

    /**
     * Retorna uma página de tópicos com base na configuração de paginação
     * fornecida.
     *
     * @param paginacao Configuração de paginação para a consulta.
     * @return Uma página contendo os tópicos encontrados.
     */
    Page<Topico> findAll(Pageable paginacao);

    /**
     * Verifica se existe algum tópico associado ao curso com o ID especificado.
     *
     * @param id ID do curso a ser verificado.
     * @return Verdadeiro se existe algum tópico associado ao curso com o ID
     *         especificado, falso caso contrário.
     */
    boolean findByCursoId(Long id);

    /**
     * Conta o número de tópicos associados ao curso com o ID especificado.
     *
     * @param id ID do curso para o qual deseja contar os tópicos.
     * @return Número de tópicos associados ao curso com o ID especificado.
     */
    long countByCursoId(Long id);
}
