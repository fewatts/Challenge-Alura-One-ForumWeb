package com.api.forumweb.app.domain.dto.dtocurso;

import com.api.forumweb.app.domain.model.Curso;

public record DadosDetalhamentoCurso(Long id, String nome, String categoria) {

    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }

}
