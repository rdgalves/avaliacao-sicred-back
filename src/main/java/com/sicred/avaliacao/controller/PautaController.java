package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.mapper.PautaMapper;
import com.sicred.avaliacao.model.Pauta;
import com.sicred.avaliacao.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;
    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        Pauta novaPauta = pautaService.criarPauta(pautaDTO);
        return ResponseEntity.created(URI.create("/pautas/" + novaPauta.getPautaId())).body(novaPauta);
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> listarPautas() {
        return ResponseEntity.ok(pautaService.listarTodasPautas());
    }
}
