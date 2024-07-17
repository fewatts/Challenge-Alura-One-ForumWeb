package com.api.forumweb.app.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.forumweb.app.domain.dto.dtousuario.DadosCadastroUsuario;
import com.api.forumweb.app.domain.model.Usuario;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Serviço responsável por operações relacionadas a usuários.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cadastra um novo usuário com base nos dados fornecidos.
     *
     * @param dados Dados de cadastro do usuário.
     * @return Um Optional contendo o usuário cadastrado, ou vazio se o email já
     *         estiver em uso.
     */
    public Optional<Usuario> cadastrarUsuario(DadosCadastroUsuario dados) {
        if (usuarioRepository.findByEmail(dados.email()) != null) {
            return Optional.empty();
        }
        var usuario = new Usuario(dados);
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }

    /**
     * Criptografa a senha fornecida usando o algoritmo BCrypt.
     *
     * @param senha A senha a ser criptografada.
     * @return A senha criptografada.
     */
    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    /**
     * Atualiza as informações de um usuário.
     *
     * @param usuario O usuário com as informações atualizadas.
     * @throws ValidacaoException Se o email já estiver em uso por outro usuário.
     */
    public void atualizarUsuario(Usuario usuario) {
        var buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (buscaUsuario != null && buscaUsuario.getId() != usuario.getId()) {
            throw new ValidacaoException("Usuário já existe");
        }
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
    }
}
