package com.api.forumweb.app.domain.service;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.model.Topico;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.validadorestopico.ValidadorPostagemTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;

/**
 * Serviço responsável por operações relacionadas a tópicos.
 */
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorPostagemTopico> validadoresTopico;

    /**
     * Cria um novo tópico com base nos dados fornecidos.
     *
     * @param dados Dados de cadastro do tópico.
     * @return O tópico criado.
     */
    public Topico criarTopico(@Valid DadosCadastroTopico dados) {
        validadoresTopico.forEach(v -> v.validar(dados));

        var topico = new Topico(dados);
        topico.setCurso(cursoRepository.getReferenceById(dados.idCurso()));
        topico.setUsuario(usuarioRepository.getReferenceById(dados.idUsuario()));
        topicoRepository.save(topico);
        return topico;
    }
}
