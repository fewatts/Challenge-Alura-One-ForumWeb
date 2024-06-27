package com.api.forumweb.app.domain.dto.dtousuario;

import com.api.forumweb.app.domain.model.Usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String usuario) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
