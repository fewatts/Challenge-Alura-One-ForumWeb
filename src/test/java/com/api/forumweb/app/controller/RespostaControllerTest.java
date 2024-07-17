package com.api.forumweb.app.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.dto.dtoresposta.DadosDetalhamentoResposta;
import com.api.forumweb.app.domain.repository.RespostaRepository;
import com.api.forumweb.app.domain.validation.validadorresposta.ValidarExistenciaTopico;
import com.api.forumweb.app.domain.validation.validadorresposta.ValidarUsuarioResposta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

/**
 * Testes para o controlador de respostas {@link RespostaController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RespostaControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<DadosCadastroRespostas> dadosCadastroRespostasJt;

        @Autowired
        private JacksonTester<DadosDetalhamentoResposta> dadosDetalhamentoRespostaJt;

        @MockBean
        private ValidarExistenciaTopico vet;

        @MockBean
        private ValidarUsuarioResposta vur;

        @MockBean
        private RespostaRepository rp;

        /**
         * Testa o POST de respostas sem corpo na requisição, esperando um retorno de
         * BAD_REQUEST.
         *
         * @throws Exception Se ocorrer algum erro durante o teste.
         */
        @Test
        @DisplayName("POST de respostas sem corpo na requisição deveria retornar BAD_REQUEST")
        @WithMockUser
        void cadastrarRespostaCenarioUm() throws Exception {
                var response = mvc.perform(post("/respostas"))
                                .andReturn().getResponse();

                assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        }

        /**
         * Testa o POST de respostas válidas, esperando um retorno de OK e os dados da
         * resposta cadastrada.
         *
         * @throws Exception Se ocorrer algum erro durante o teste.
         */
        @Test
        @DisplayName("POST de respostas válidas deve retornar OK")
        @WithMockUser
        void cadastrarRespostaCenarioDois() throws Exception {

                doNothing().when(vet).validar(any());
                doNothing().when(vur).validar(any());

                var data = LocalDateTime.now();

                var dados = new DadosCadastroRespostas("Testando", 1L, 1L, data, "Essa e a solucao");

                var response = mvc.perform(
                                post("/respostas")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(dadosCadastroRespostasJt.write(dados).getJson()))
                                .andReturn().getResponse();

                var jsonEsperado = dadosDetalhamentoRespostaJt.write(
                                new DadosDetalhamentoResposta(null, "Testando", data, "Essa e a solucao", 1L))
                                .getJson();

                var jsonEsperadoNode = new ObjectMapper().readTree(jsonEsperado);
                ((ObjectNode) jsonEsperadoNode).remove("dataCriacao");

                var jsonResponseNode = new ObjectMapper().readTree(response.getContentAsString());
                ((ObjectNode) jsonResponseNode).remove("dataCriacao");

                assertThat(jsonResponseNode).isEqualTo(jsonEsperadoNode);
                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        }
}
