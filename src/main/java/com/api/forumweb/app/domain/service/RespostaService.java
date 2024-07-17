package com.api.forumweb.app.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.model.Resposta;
import com.api.forumweb.app.domain.repository.RespostaRepository;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.validadorresposta.ValidarExistenciaTopico;
import com.api.forumweb.app.domain.validation.validadorresposta.ValidarUsuarioResposta;

/**
 * Serviço responsável por operações relacionadas a respostas em tópicos.
 */
@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidarExistenciaTopico validarExistenciaTopico;

    @Autowired
    private ValidarUsuarioResposta validarExistenciaUsuario;

    /**
     * Cadastra uma nova resposta para um tópico.
     *
     * @param dados Dados da resposta a ser cadastrada.
     * @return A resposta cadastrada.
     */
    public Resposta cadastrarResposta(DadosCadastroRespostas dados) {
        validarExistencia(dados);
        Resposta resposta = criarResposta(dados);
        respostaRepository.save(resposta);
        return resposta;
    }

    /**
     * Valida a existência do tópico e do usuário associado à resposta.
     *
     * @param dados Dados da resposta a ser validada.
     */
    private void validarExistencia(DadosCadastroRespostas dados) {
        validarExistenciaTopico.validar(dados);
        validarExistenciaUsuario.validar(dados);
    }

    /**
     * Cria uma instância de Resposta com base nos dados fornecidos.
     *
     * @param dados Dados da resposta a ser criada.
     * @return A resposta criada.
     */
    private Resposta criarResposta(DadosCadastroRespostas dados) {
        Resposta resposta = new Resposta(dados);
        resposta.setTopico(topicoRepository.getReferenceById(dados.idTopico()));
        resposta.setUsuario(usuarioRepository.getReferenceById(dados.idUsuario()));
        return resposta;
    }
}
