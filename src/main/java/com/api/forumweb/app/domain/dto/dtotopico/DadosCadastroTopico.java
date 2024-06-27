package com.api.forumweb.app.domain.dto.dtotopico;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(

        @NotNull String titulo,
        @NotNull String mensagem,
        @NotNull Boolean status,
        @NotNull Long idCurso,
        @NotNull Long idUsuario

) {

}
