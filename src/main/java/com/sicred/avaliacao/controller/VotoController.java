package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.ResultadoVotacaoDTO;
import com.sicred.avaliacao.dto.VotoDTO;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotoController {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarVoto(@Valid @RequestBody VotoRequestDTO votoRequestDTO) {
        votoService.registrarVoto(votoRequestDTO);
        return ResponseEntity.ok("Voto enviado para processamento.");
    }

    @GetMapping("/resultado/{pautaId}")
    public ResponseEntity<ResultadoVotacaoDTO> obterResultadoVotacao(@PathVariable Long pautaId) {
        ResultadoVotacaoDTO resultado = votoService.contabilizarVotos(pautaId);
        return ResponseEntity.ok(resultado);
    }
}