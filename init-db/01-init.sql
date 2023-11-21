CREATE TABLE Associado (
    associado_id SERIAL PRIMARY KEY,  -- ID único para cada associado
    nome VARCHAR(255) NOT NULL,       -- Nome completo do associado
    email VARCHAR(255),               -- Email do associado (opcional)
    cpf VARCHAR(11) NOT NULL          -- CPF do associado (sem pontos ou traços)
);

COMMENT ON COLUMN Associado.associado_id IS 'Identificador único do associado';
COMMENT ON COLUMN Associado.nome IS 'Nome completo do associado';
COMMENT ON COLUMN Associado.email IS 'Email do associado, pode ser nulo';
COMMENT ON COLUMN Associado.cpf IS 'CPF do associado, formato apenas números';


CREATE TABLE Pauta (
    pauta_id SERIAL PRIMARY KEY,      -- ID único para cada pauta
    titulo VARCHAR(255) NOT NULL,     -- Título da pauta
    descricao TEXT                    -- Descrição detalhada da pauta
);

COMMENT ON COLUMN Pauta.pauta_id IS 'Identificador único da pauta';
COMMENT ON COLUMN Pauta.titulo IS 'Título da pauta';
COMMENT ON COLUMN Pauta.descricao IS 'Descrição detalhada da pauta';


CREATE TABLE SessaoVotacao (
    sessao_id SERIAL PRIMARY KEY,     -- ID único para cada sessão de votação
    pauta_id INT REFERENCES Pauta(pauta_id) NOT NULL, -- ID da pauta vinculada à sessão
    inicio TIMESTAMP NOT NULL,        -- Horário de início da sessão de votação
    duracao INT DEFAULT 1,            -- Duração da sessão em minutos
    status VARCHAR(50) DEFAULT 'PENDENTE' CHECK (status IN ('FECHADO', 'ABERTO', 'PENDENTE')) -- Estado da sessão (PENDENTE, FECHADO ou ABERTO)
);

CREATE OR REPLACE FUNCTION ajustar_campos()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status IS NULL THEN
        NEW.status := 'PENDENTE';
    END IF;
    IF NEW.duracao IS NULL THEN
        NEW.duracao := 1;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER ajustar_campos_antes_de_inserir
BEFORE INSERT ON SessaoVotacao
FOR EACH ROW EXECUTE FUNCTION ajustar_campos();

COMMENT ON COLUMN SessaoVotacao.sessao_id IS 'Identificador único da sessão de votação';
COMMENT ON COLUMN SessaoVotacao.pauta_id IS 'ID da pauta associada a esta sessão';
COMMENT ON COLUMN SessaoVotacao.inicio IS 'Timestamp de início da sessão de votação';
COMMENT ON COLUMN SessaoVotacao.duracao IS 'Duração da sessão de votação em minutos';
COMMENT ON COLUMN SessaoVotacao.status IS 'Estado atual da sessão (pendente/aberta/fechada)';


CREATE TABLE Voto (
    voto_id SERIAL PRIMARY KEY,       -- ID único para cada voto
    sessao_id INT REFERENCES SessaoVotacao(sessao_id), -- ID da sessão de votação
    associado_id INT REFERENCES Associado(associado_id), -- ID do associado votante
    voto VARCHAR(3) CHECK (voto IN ('Sim', 'Não')) -- Valor do voto ('Sim' ou 'Não')
);

COMMENT ON COLUMN Voto.voto_id IS 'Identificador único do voto';
COMMENT ON COLUMN Voto.sessao_id IS 'ID da sessão de votação associada';
COMMENT ON COLUMN Voto.associado_id IS 'ID do associado que está votando';
COMMENT ON COLUMN Voto.voto IS 'Valor do voto (Sim ou Não)';
