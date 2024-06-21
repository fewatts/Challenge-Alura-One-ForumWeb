package com.api.forumweb.app.domain.validation.validadorcurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtocurso.DadosCadastroCurso;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarCursoDuplicado {
    
    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DadosCadastroCurso dados){
        if(cursoRepository.existsByNomeIgnoreCase(dados.nome())){
            throw new ValidacaoException("O curso '" + dados.nome() + "' já existe.");
        }
    }

}
