package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarExistenciaCurso implements ValidadorPostagemTopico{

    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DadosCadastroTopico dados) {
        var curso = cursoRepository.findById(dados.idCurso());
        if(!curso.isPresent()){
            throw new ValidacaoException("Esse curso não existe na base de dados");
        }
        
    }
    
}
