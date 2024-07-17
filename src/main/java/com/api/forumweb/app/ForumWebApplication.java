package com.api.forumweb.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada principal para a aplicação ForumWeb.
 * Esta classe configura e inicializa o Spring Boot para executar a aplicação.
 */
@SpringBootApplication
public class ForumWebApplication {

	/**
     * Método principal que inicia a aplicação Spring Boot.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
	public static void main(String[] args) {
		SpringApplication.run(ForumWebApplication.class, args);
	}

}
