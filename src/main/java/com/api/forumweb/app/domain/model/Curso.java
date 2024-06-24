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

@Table(name = "cursos")
@Entity(name = "Curso")
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

    public Curso(DadosCadastroCurso dados){
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }

    public void atualizar(DadosCadastroCurso dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.categoria() != null){
            this.categoria = dados.categoria();
        }
    }

}
