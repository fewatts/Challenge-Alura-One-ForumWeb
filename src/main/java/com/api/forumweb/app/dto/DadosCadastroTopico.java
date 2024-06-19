package com.api.forumweb.app.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(

        @NotBlank String titulo,
        
        @NotBlank String mensagem,
        
        LocalDateTime dataCriacao,
        
        @NotNull boolean status

    ) {

}
