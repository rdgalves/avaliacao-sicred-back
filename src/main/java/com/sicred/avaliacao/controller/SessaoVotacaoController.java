package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {

    // POST: Abrir uma nova sessão de votação
    @PostMapping
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestBody SessaoVotacaoDTO sessaoVotacaoDto) {
        return ResponseEntity.ok(new SessaoVotacao());
    }

    // GET: Listar sessões de votação
    @GetMapping
    public ResponseEntity<List<SessaoVotacao>> listarSessoes() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}

