package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {
    private final SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@Valid @RequestBody SessaoVotacaoRequestDTO sessaoVotacaoRequestDTO) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.criarSessaoVotacao(sessaoVotacaoRequestDTO);
        return ResponseEntity.created(URI.create("/sessoes/" + sessaoVotacao.getSessaoId())).body(sessaoVotacao);
    }

    @GetMapping
    public ResponseEntity<List<SessaoVotacaoDTO>> listarSessoes() {
        return ResponseEntity.ok(sessaoVotacaoService.listarTodasSessoes());
    }
}

