package com.sicred.avaliacao.repository;

import com.sicred.avaliacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    boolean existsByPauta_PautaId(Long pautaId);
}
