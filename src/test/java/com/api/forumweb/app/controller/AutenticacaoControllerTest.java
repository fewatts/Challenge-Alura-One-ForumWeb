package com.api.forumweb.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.api.forumweb.app.domain.dto.dtousuario.DadosAutenticacao;
import com.api.forumweb.app.domain.model.Usuario;
import com.api.forumweb.app.infra.security.TokenService;

/**
 * Testes para o controlador de autenticação {@link AutenticacaoController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AutenticacaoControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<DadosAutenticacao> DadosAutenticacaoJt;

        @MockBean
        private TokenService tokenService;

        @MockBean
        private AuthenticationManager manager;

        /**
         * Teste de autenticação com sucesso.
         *
         * @throws Exception Se ocorrer algum erro durante o teste.
         */
        @Test
        @DisplayName("Teste de autenticação com sucesso")
        void autenticacaoCenarioUm() throws Exception {

                var dadosAutenticacao = new DadosAutenticacao("fulano@gmail.com", "Hdkset43%4F");

                var authenticationMock = mock(Authentication.class);
                when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                                .thenReturn(authenticationMock);

                var usuarioMock = new Usuario(/* dados mockados do usuário */);
                when(authenticationMock.getPrincipal()).thenReturn(usuarioMock);

                var tokenJWTMock = "mocked-jwt-token";
                when(tokenService.gerarToken(usuarioMock)).thenReturn(tokenJWTMock);

                var result = mvc.perform(
                                post("/login")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(DadosAutenticacaoJt.write(dadosAutenticacao).getJson()))
                                .andExpect(status().isOk())
                                .andReturn();

                var content = result.getResponse().getContentAsString();
                assertThat(content).contains(tokenJWTMock);
        }

        /**
         * Teste de autenticação com falha.
         *
         * @throws Exception Se ocorrer algum erro durante o teste.
         */
        @Test
        @DisplayName("Teste de autenticação com falha")
        void autenticacaoCenarioDois() throws Exception {
                var dadosAutenticacao = new DadosAutenticacao("fulano@gmail.com", "SenhaIncorreta");

                when(manager.authenticate(any()))
                                .thenThrow(new BadCredentialsException("Credenciais inválidas"));

                mvc.perform(post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(DadosAutenticacaoJt.write(dadosAutenticacao).getJson()))
                                .andExpect(status().isUnauthorized());
        }
}
