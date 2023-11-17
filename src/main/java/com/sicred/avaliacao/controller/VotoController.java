package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.ResultadoVotacaoDTO;
import com.sicred.avaliacao.dto.VotoDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.model.Voto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/votos")
public class VotoController {

    // POST: Registrar um voto
    @PostMapping
    public ResponseEntity<Voto> registrarVoto(@RequestBody VotoDTO votoDto) {
        return ResponseEntity.ok(new Voto());
    }

    // GET: Contabilizar e mostrar o resultado da votação de uma sessão
    @GetMapping("/resultado/{sessaoId}")
    public ResponseEntity<ResultadoVotacaoDTO> resultadoVotacao(@PathVariable Long sessaoId) {
        return ResponseEntity.ok(new ResultadoVotacaoDTO());
    }
}
