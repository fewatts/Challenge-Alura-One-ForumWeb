CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE topicos 
ADD COLUMN id_usuario BIGINT,
ADD CONSTRAINT fk_topico_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id);

ALTER TABLE respostas 
ADD COLUMN id_usuario BIGINT,
ADD CONSTRAINT fk_resposta_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id);
