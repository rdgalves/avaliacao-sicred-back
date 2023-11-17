package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.model.Pauta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    // POST: Criar uma nova pauta
    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        return ResponseEntity.ok(pauta);
    }

    // GET: Listar todas as pautas
    @GetMapping
    public ResponseEntity<List<Pauta>> listarPautas() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
