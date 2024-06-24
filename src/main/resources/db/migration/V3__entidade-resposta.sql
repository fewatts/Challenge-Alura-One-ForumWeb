CREATE TABLE respostas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem VARCHAR(255) NOT NULL,
    id_topico BIGINT NOT NULL,
    data_criacao DATETIME NOT NULL,
    solucao VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (id_topico) REFERENCES topicos(id)
);
