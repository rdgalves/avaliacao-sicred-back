package com.sicred.avaliacao.repository;

import com.sicred.avaliacao.model.Associado;
import com.sicred.avaliacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByAssociadoAndSessaoSessaoId(Associado associado, Long sessaoId);

    List<Voto> findBySessaoPautaPautaId(Long pautaId);
}
