package com.api.forumweb.app.domain.dto.dtocurso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(

        @NotBlank String nome,
        
        @NotBlank String categoria

    ) {

}
