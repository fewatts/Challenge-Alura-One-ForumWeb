package com.api.forumweb.app.controller;

import java.util.List;
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

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.dto.dtotopico.DadosDetalhamentoTopico;
import com.api.forumweb.app.domain.dto.dtotopico.DadosListagemTopico;
import com.api.forumweb.app.domain.model.Topico;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.validadorestopico.ValidadorPostagemTopico;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorPostagemTopico> validadoresTopico;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = { "dataCriacao" }, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalharTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder) {
        validadoresTopico.forEach(v -> v.validar(dados));
        
        var topico = new Topico(dados);
        topico.setCurso(cursoRepository.getReferenceById(dados.idCurso()));
        topico.setUsuario(usuarioRepository.getReferenceById(dados.idUsuario()));
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosCadastroTopico dados){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topico.get().atualizar(dados);
        }
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico.get()));
    }

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
