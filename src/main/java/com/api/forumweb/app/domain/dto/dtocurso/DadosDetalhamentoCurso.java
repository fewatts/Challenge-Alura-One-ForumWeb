package com.api.forumweb.app.domain.dto.dtocurso;

import com.api.forumweb.app.domain.model.Curso;

/**
 * Dados para o detalhamento de um curso.
 *
 * @param id O ID do curso.
 * @param nome O nome do curso.
 * @param categoria A categoria do curso.
 */
public record DadosDetalhamentoCurso(Long id, String nome, String categoria) {

    /**
     * Construtor que cria uma instância de DadosDetalhamentoCurso a partir de um objeto Curso.
     *
     * @param curso O objeto Curso a partir do qual os dados de detalhamento serão extraídos.
     */
    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
