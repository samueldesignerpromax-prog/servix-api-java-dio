
package com.servix.api.controller;

import com.servix.api.model.Servico;
import com.servix.api.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Servico> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> criar(
            @Valid @RequestBody Servico servico) {
        Servico criado = service.criar(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Servico servico) {

        return service.atualizar(id, servico)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (service.excluir(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/descricao")
    public ResponseEntity<Map<String, String>> descrever(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    Map.of("descricao",
                            service.descreverServico(id)));
        } catch (com.servix.api.service.ServicoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}
