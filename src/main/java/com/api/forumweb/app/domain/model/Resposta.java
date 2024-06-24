package com.api.forumweb.app.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String mensagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_topico")
    private Topico topico;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    private String solucao;
    
}
