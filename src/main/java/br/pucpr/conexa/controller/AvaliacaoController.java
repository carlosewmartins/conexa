package br.pucpr.conexa.controller;

import br.pucpr.conexa.model.Avaliacao;
import br.pucpr.conexa.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@Tag(name = "Avaliações", description = "Endpoints para gerenciamento de avaliações de eventos")
@SecurityRequirement(name = "bearerAuth")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    @Operation(summary = "Listar todas as avaliações", description = "Retorna uma lista com todas as avaliações cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    public ResponseEntity<List<Avaliacao>> listarTodos() {
        return ResponseEntity.ok(avaliacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar avaliação por ID", description = "Retorna uma avaliação específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação encontrada"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar avaliações por usuário", description = "Retorna todas as avaliações feitas por um usuário")
    public ResponseEntity<List<Avaliacao>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(avaliacaoService.buscarPorUsuario(usuarioId));
    }

    @GetMapping("/evento/{eventoId}")
    @Operation(summary = "Buscar avaliações por evento", description = "Retorna todas as avaliações de um evento")
    public ResponseEntity<List<Avaliacao>> buscarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(avaliacaoService.buscarPorEvento(eventoId));
    }

    @PostMapping
    @Operation(summary = "Criar nova avaliação", description = "Cria uma nova avaliação para um evento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Avaliacao> criar(@Valid @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.criar(avaliacao));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar avaliação", description = "Atualiza os dados de uma avaliação existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @Valid @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoService.atualizar(id, avaliacao));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar avaliação", description = "Remove uma avaliação do sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Avaliação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
