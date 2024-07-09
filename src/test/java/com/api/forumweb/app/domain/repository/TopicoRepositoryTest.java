package com.api.forumweb.app.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.api.forumweb.app.domain.dto.dtocurso.DadosCadastroCurso;
import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.dto.dtousuario.DadosCadastroUsuario;
import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.model.Topico;
import com.api.forumweb.app.domain.model.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TopicoRepositoryTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Curso curso;

    private Usuario usuario;

    @BeforeEach
    public void setup() {
        this.curso = criarCurso("Java Avançado", "Programação");
        this.usuario = criarUsuario("fulano@example.com", "Fulano Silva", "Senha@123");
    }

    @Test
    @DisplayName("Deve devolver o número de tópicos que existem com um certo curso, nesse caso, zero")
    void countByCursoIdCenarioUm() {
        var existeTopicoNesseCurso = topicoRepository.countByCursoId(curso.getId());
        assertEquals(existeTopicoNesseCurso, 0);
    }

    @Test
    @DisplayName("Deve devolver o número de tópicos que existem com um certo curso, nesse caso, 2")
    void countByCursoIdCenarioDois() {
        criarTopico("Dúvida 1", "Descrição da dúvida 1", true, curso.getId(), usuario.getId());
        criarTopico("Dúvida 2", "Descrição da dúvida 2", true, curso.getId(), usuario.getId());

        var existeTopicoNesseCurso = topicoRepository.countByCursoId(curso.getId());
        assertEquals(existeTopicoNesseCurso, 2);
    }

    private Curso criarCurso(String nome, String categoria) {
        DadosCadastroCurso dadosCurso = new DadosCadastroCurso(nome, categoria);
        var curso = new Curso(null, dadosCurso.nome(), dadosCurso.categoria());
        return entityManager.persist(curso);
    }

    private Usuario criarUsuario(String email, String nome, String senha) {
        DadosCadastroUsuario dadosUsuario = new DadosCadastroUsuario(nome, email, senha);
        Usuario usuario = new Usuario(null, dadosUsuario.nome(), dadosUsuario.email(), dadosUsuario.senha(), null,
                null);
        return entityManager.persist(usuario);
    }

    private Topico criarTopico(String titulo, String mensagem, boolean status, Long idCurso, Long idUsuario) {
        DadosCadastroTopico dadosTopico = new DadosCadastroTopico(titulo, mensagem, status, idCurso, idUsuario);
        var curso = entityManager.find(Curso.class, dadosTopico.idCurso());
        var usuario = entityManager.find(Usuario.class, dadosTopico.idUsuario());

        Topico topico = new Topico(
                null, 
                dadosTopico.titulo(),
                dadosTopico.mensagem(),
                LocalDateTime.now(),
                dadosTopico.status(),
                curso, 
                usuario, null);
        return entityManager.persist(topico);
    }

}
