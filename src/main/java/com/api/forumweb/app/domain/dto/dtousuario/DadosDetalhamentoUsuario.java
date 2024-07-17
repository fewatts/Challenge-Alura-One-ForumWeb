package com.api.forumweb.app.domain.dto.dtousuario;

import com.api.forumweb.app.domain.model.Usuario;

/**
 * DTO para detalhamento de informações de usuário.
 *
 * @param id O ID do usuário.
 * @param nome O nome do usuário.
 * @param usuario O nome de usuário (username) do usuário.
 */
public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String usuario
) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
