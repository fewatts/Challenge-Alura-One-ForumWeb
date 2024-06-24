package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroRespostas(

        @NotBlank String mensagem,

        @NotNull Long idTopico,

        LocalDateTime dataCriacao,

        @NotBlank String solucao
    ) {

}
