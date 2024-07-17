package com.api.forumweb.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.forumweb.app.domain.dto.dtousuario.DadosCadastroUsuario;
import com.api.forumweb.app.domain.dto.dtousuario.DadosDetalhamentoUsuario;
import com.api.forumweb.app.domain.model.Usuario;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista todos os usuários com paginação.
     *
     * @param paginacao Configuração de paginação.
     * @return Página de usuários listados.
     */
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listarUsuarios(
            @PageableDefault(size = 10, sort = { "nome" }, direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = usuarioRepository.findAll(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Detalha um usuário específico pelo ID.
     *
     * @param id ID do usuário a ser detalhado.
     * @return Detalhes do usuário.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoUsuario(usuario));
    }

    /**
     * Cadastra um novo usuário.
     *
     * @param dados Dados do usuário a ser cadastrado.
     * @param uriBuilder Builder para criar a URI de resposta.
     * @return ResponseEntity contendo os detalhes do usuário cadastrado.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrarUsuario(dados);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.get().getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario.get()));
    }

    /**
     * Atualiza um usuário existente pelo ID.
     *
     * @param id ID do usuário a ser atualizado.
     * @param dados Novos dados do usuário.
     * @return ResponseEntity contendo os detalhes do usuário atualizado.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizarUsuario(@PathVariable Long id,
            @RequestBody @Valid DadosCadastroUsuario dados) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioService.atualizarUsuario(usuario.get());
        }
        return ResponseEntity.ok().body(new DadosDetalhamentoUsuario(usuario.get()));
    }

}
