package com.api.forumweb.app.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.forumweb.app.domain.dto.dtousuario.DadosCadastroUsuario;
import com.api.forumweb.app.domain.model.Usuario;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> cadastrarUsuario(DadosCadastroUsuario dados){

        if(usuarioRepository.findByEmail(dados.email()) != null){
            return Optional.empty();
        }

        Usuario usuario = new Usuario(dados);

        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        return Optional.of(usuarioRepository.save(usuario));
    }

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);
    }

    public void atualizarUsuario(Usuario usuario) {
        var buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());

        if(buscaUsuario != null && buscaUsuario.getId() != usuario.getId()){
            throw new ValidacaoException("Usuário já existe");
        }

        usuario.setSenha(criptografarSenha(usuario.getSenha()));
    }

    
}
