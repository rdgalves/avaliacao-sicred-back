package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoResponseDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@Valid @RequestBody SessaoVotacaoRequestDTO sessaoVotacaoRequestDTO) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.criarSessaoVotacao(sessaoVotacaoRequestDTO);
        return ResponseEntity.created(URI.create("/sessoes/" + sessaoVotacao.getSessaoId())).body(sessaoVotacao);
    }

    // GET: Listar sessões de votação
    @GetMapping
    public ResponseEntity<List<SessaoVotacao>> listarSessoes() {
        return ResponseEntity.ok(sessaoVotacaoService.listarTodasSessoes());
    }
}

