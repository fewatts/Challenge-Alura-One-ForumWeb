package com.api.forumweb.app.controller;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.dto.dtotopico.DadosDetalhamentoTopico;
import com.api.forumweb.app.domain.dto.dtotopico.DadosListagemTopico;
import com.api.forumweb.app.domain.model.Topico;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    /**
     * Lista todos os tópicos com paginação.
     *
     * @param paginacao Configuração de paginação.
     * @return Página de tópicos listados.
     */
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = { "dataCriacao" }, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Detalha um tópico específico pelo ID.
     *
     * @param id ID do tópico a ser detalhado.
     * @return Detalhes do tópico.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalharTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico));
    }

    /**
     * Cadastra um novo tópico.
     *
     * @param dados Dados do tópico a ser cadastrado.
     * @param uriBuilder Builder para criar a URI de resposta.
     * @return ResponseEntity contendo os detalhes do tópico cadastrado.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder) {
        var topico = topicoService.criarTopico(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    /**
     * Atualiza um tópico existente pelo ID.
     *
     * @param id ID do tópico a ser atualizado.
     * @param dados Novos dados do tópico.
     * @return ResponseEntity contendo os detalhes do tópico atualizado.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosCadastroTopico dados){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topico.get().atualizar(dados);
        }
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico.get()));
    }

    /**
     * Deleta um tópico pelo ID.
     *
     * @param id ID do tópico a ser deletado.
     * @return ResponseEntity com o status da operação.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deletarTopico(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
