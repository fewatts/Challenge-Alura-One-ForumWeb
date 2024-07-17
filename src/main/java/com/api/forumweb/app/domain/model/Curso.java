package com.api.forumweb.app.domain.model;

import com.api.forumweb.app.domain.dto.dtocurso.DadosCadastroCurso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade Curso que representa um curso no sistema.
 */
@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String categoria;

    /**
     * Construtor para criar um Curso a partir dos dados de cadastro.
     *
     * @param dados Os dados de cadastro do curso.
     */
    public Curso(DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }

    /**
     * Atualiza os atributos do curso com base nos novos dados fornecidos.
     *
     * @param dados Os novos dados de cadastro do curso.
     */
    public void atualizar(DadosCadastroCurso dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
    }
}
