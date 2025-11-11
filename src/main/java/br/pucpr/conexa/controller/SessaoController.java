package br.pucpr.conexa.controller;

import br.pucpr.conexa.model.Sessao;
import br.pucpr.conexa.service.SessaoService;
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
@RequestMapping("/api/sessoes")
@Tag(name = "Sessões", description = "Endpoints para gerenciamento de sessões/agenda de eventos")
@SecurityRequirement(name = "bearerAuth")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @GetMapping
    @Operation(summary = "Listar todas as sessões", description = "Retorna uma lista com todas as sessões cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de sessões retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    public ResponseEntity<List<Sessao>> listarTodos() {
        return ResponseEntity.ok(sessaoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sessão por ID", description = "Retorna uma sessão específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sessão encontrada"),
        @ApiResponse(responseCode = "404", description = "Sessão não encontrada")
    })
    public ResponseEntity<Sessao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sessaoService.buscarPorId(id));
    }

    @GetMapping("/evento/{eventoId}")
    @Operation(summary = "Buscar sessões por evento", description = "Retorna todas as sessões de um evento")
    public ResponseEntity<List<Sessao>> buscarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(sessaoService.buscarPorEvento(eventoId));
    }

    @GetMapping("/palestrante/{palestranteId}")
    @Operation(summary = "Buscar sessões por palestrante", description = "Retorna todas as sessões de um palestrante")
    public ResponseEntity<List<Sessao>> buscarPorPalestrante(@PathVariable Long palestranteId) {
        return ResponseEntity.ok(sessaoService.buscarPorPalestrante(palestranteId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar nova sessão", description = "Cria uma nova sessão para um evento (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sessão criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Sessao> criar(@Valid @RequestBody Sessao sessao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoService.criar(sessao));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar sessão", description = "Atualiza os dados de uma sessão existente (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sessão atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Sessão não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Sessao> atualizar(@PathVariable Long id, @Valid @RequestBody Sessao sessao) {
        return ResponseEntity.ok(sessaoService.atualizar(id, sessao));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar sessão", description = "Remove uma sessão do sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Sessão deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Sessão não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sessaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
