package com.sicred.avaliacao.repository;

import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    boolean existsByPauta_PautaId(Long pautaId);

    List<SessaoVotacao> findByInicioLessThanEqualAndStatus(LocalDateTime agora, StatusEnum status);

    List<SessaoVotacao> findByStatus(StatusEnum statusEnum);

}
