package com.api.forumweb.app.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.forumweb.app.domain.repository.UsuarioRepository;

/**
 * Serviço responsável pela autenticação de usuários.
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário com base no nome de usuário (no caso, o
     * e-mail).
     *
     * @param username O nome de usuário (e-mail) do usuário a ser carregado.
     * @return UserDetails contendo os detalhes do usuário encontrado.
     * @throws UsernameNotFoundException Se o usuário com o nome de usuário
     *                                   especificado não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }
}
