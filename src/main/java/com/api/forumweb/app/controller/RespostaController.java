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
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.validadorresposta.ValidarExistenciaTopico;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {
    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired 
    UsuarioRepository usuarioRepository;

    @Autowired
    private ValidarExistenciaTopico validarExistenciaTopico;

    @GetMapping
    public ResponseEntity<Page<DadosListagemRespostas>> listarRespostas(
            @PageableDefault(size = 10, sort = { "dataCriacao" }, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = respostaRepository.findAll(paginacao).map(DadosListagemRespostas::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoResposta> detalharResposta(@PathVariable Long id){
        var resposta = respostaRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoResposta(resposta));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoResposta> cadastrarResposta(@RequestBody @Valid DadosCadastroRespostas dados,
            UriComponentsBuilder uriBuilder) {
        validarExistenciaTopico.validar(dados);
        var resposta = new Resposta(dados);
        resposta.setTopico(topicoRepository.getReferenceById(dados.idTopico()));
        resposta.setUsuario(usuarioRepository.getReferenceById(dados.idUsuario()));
        respostaRepository.save(resposta);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoResposta> atualizarResposta(@PathVariable Long id, @RequestBody @Valid DadosCadastroRespostas dados){
        Optional<Resposta> resposta = respostaRepository.findById(id);
        if(resposta.isPresent()){
            System.out.println(resposta);
            resposta.get().atualizar(dados);
        }
        return ResponseEntity.ok().body(new DadosDetalhamentoResposta(resposta.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deletarResposta(@PathVariable Long id){
        Optional<Resposta> resposta = respostaRepository.findById(id);
        if(resposta.isPresent()){
            respostaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
