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

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.dto.dtoresposta.DadosDetalhamentoResposta;
import com.api.forumweb.app.domain.dto.dtoresposta.DadosListagemRespostas;
import com.api.forumweb.app.domain.model.Resposta;
import com.api.forumweb.app.domain.repository.RespostaRepository;
import com.api.forumweb.app.domain.service.RespostaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Controller responsável por gerenciar respostas.
 */
@RestController
@RequestMapping("respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private RespostaService respostaService;

    /**
     * Lista todas as respostas com paginação.
     *
     * @param paginacao Configurações de paginação e ordenação.
     * @return ResponseEntity contendo uma página de listagem de respostas.
     */
    @GetMapping
    public ResponseEntity<Page<DadosListagemRespostas>> listarRespostas(
            @PageableDefault(size = 10, sort = { "dataCriacao" }, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = respostaRepository.findAll(paginacao).map(DadosListagemRespostas::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Detalha uma resposta específica.
     *
     * @param id ID da resposta a ser detalhada.
     * @return ResponseEntity contendo os detalhes da resposta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoResposta> detalharResposta(@PathVariable Long id) {
        var resposta = respostaRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoResposta(resposta));
    }

    /**
     * Cadastra uma nova resposta.
     *
     * @param dados      Dados da resposta a ser cadastrada.
     * @param uriBuilder Builder para criar a URI de resposta.
     * @return ResponseEntity contendo os detalhes da resposta cadastrada.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoResposta> cadastrarResposta(@RequestBody @Valid DadosCadastroRespostas dados,
            UriComponentsBuilder uriBuilder) {
        Resposta resposta = respostaService.cadastrarResposta(dados);
        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    /**
     * Atualiza uma resposta existente.
     *
     * @param id    ID da resposta a ser atualizada.
     * @param dados Novos dados da resposta.
     * @return ResponseEntity contendo os detalhes da resposta atualizada.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoResposta> atualizarResposta(@PathVariable Long id,
            @RequestBody @Valid DadosCadastroRespostas dados) {
        Optional<Resposta> resposta = respostaRepository.findById(id);
        if (resposta.isPresent()) {
            resposta.get().atualizar(dados);
        }
        return ResponseEntity.ok().body(new DadosDetalhamentoResposta(resposta.get()));
    }

    /**
     * Deleta uma resposta.
     *
     * @param id ID da resposta a ser deletada.
     * @return ResponseEntity com o status da operação.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deletarResposta(@PathVariable Long id) {
        Optional<Resposta> resposta = respostaRepository.findById(id);
        if (resposta.isPresent()) {
            respostaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
