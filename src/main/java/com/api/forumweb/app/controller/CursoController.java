package com.api.forumweb.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.forumweb.app.domain.dto.dtocurso.DadosCadastroCurso;
import com.api.forumweb.app.domain.dto.dtocurso.DadosDetalhamentoCurso;
import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.validation.validadorcurso.ValidarCursoDuplicado;
import com.api.forumweb.app.domain.validation.validadorcurso.ValidarExistenciaDeCursoComTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Controller responsável por gerenciar cursos.
 */
@RestController
@RequestMapping("cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ValidarExistenciaDeCursoComTopico validarExistenciaDeCursoComTopico;

    @Autowired
    private ValidarCursoDuplicado validarCursoDuplicado;

    /**
     * Lista todos os cursos com paginação.
     *
     * @param paginacao Configurações de paginação e ordenação.
     * @return ResponseEntity contendo uma página de detalhes dos cursos.
     */
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCurso>> listarCursos(
            @PageableDefault(size = 10, sort = { "nome" }, direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = cursoRepository.findAll(paginacao).map(DadosDetalhamentoCurso::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Detalha um curso específico.
     *
     * @param id ID do curso a ser detalhado.
     * @return ResponseEntity contendo os detalhes do curso.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCurso> detalharCurso(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoCurso(curso));
    }

    /**
     * Cadastra um novo curso.
     *
     * @param dados      Dados do curso a ser cadastrado.
     * @param uriBuilder Builder para criar a URI de resposta.
     * @return ResponseEntity contendo os detalhes do curso cadastrado.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados,
            UriComponentsBuilder uriBuilder) {
        validarCursoDuplicado.validar(dados);
        var curso = new Curso(dados);
        cursoRepository.save(curso);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    /**
     * Atualiza um curso existente.
     *
     * @param id    ID do curso a ser atualizado.
     * @param dados Novos dados do curso.
     * @return ResponseEntity contendo os detalhes do curso atualizado.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> atualizarCurso(@PathVariable Long id,
            @RequestBody @Valid DadosCadastroCurso dados) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            curso.get().atualizar(dados);
        }
        return ResponseEntity.ok().body(new DadosDetalhamentoCurso(curso.get()));
    }

    /**
     * Deleta um curso.
     *
     * @param id ID do curso a ser deletado.
     * @return ResponseEntity com o status da operação.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deletarCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            if (validarExistenciaDeCursoComTopico.validar(curso.get())) {
                cursoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
