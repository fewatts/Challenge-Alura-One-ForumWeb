package com.api.forumweb.app.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.forumweb.app.domain.model.Usuario;

/**
 * Repositório para entidade Usuario. Realiza operações de persistência e
 * consulta no banco de dados para a entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo seu endereço de e-mail.
     *
     * @param email Endereço de e-mail do usuário a ser buscado.
     * @return O usuário encontrado com o endereço de e-mail especificado, ou null
     *         se não encontrado.
     */
    Usuario findByEmail(String email);

    /**
     * Retorna uma página de todos os usuários com base na configuração de paginação
     * fornecida.
     *
     * @param paginacao Configuração de paginação para a consulta.
     * @return Uma página contendo os usuários encontrados.
     */
    Page<Usuario> findAll(Pageable paginacao);

}
