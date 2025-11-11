package br.pucpr.conexa.controller;

import br.pucpr.conexa.model.Palestrante;
import br.pucpr.conexa.service.PalestranteService;
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
@RequestMapping("/api/palestrantes")
@Tag(name = "Palestrantes", description = "Endpoints para gerenciamento de palestrantes")
@SecurityRequirement(name = "bearerAuth")
public class PalestranteController {

    @Autowired
    private PalestranteService palestranteService;

    @GetMapping
    @Operation(summary = "Listar todos os palestrantes", description = "Retorna uma lista com todos os palestrantes cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de palestrantes retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    public ResponseEntity<List<Palestrante>> listarTodos() {
        return ResponseEntity.ok(palestranteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar palestrante por ID", description = "Retorna um palestrante específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Palestrante encontrado"),
        @ApiResponse(responseCode = "404", description = "Palestrante não encontrado")
    })
    public ResponseEntity<Palestrante> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(palestranteService.buscarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar novo palestrante", description = "Cria um novo palestrante no sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Palestrante criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Palestrante> criar(@Valid @RequestBody Palestrante palestrante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(palestranteService.criar(palestrante));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar palestrante", description = "Atualiza os dados de um palestrante existente (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Palestrante atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Palestrante não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Palestrante> atualizar(@PathVariable Long id, @Valid @RequestBody Palestrante palestrante) {
        return ResponseEntity.ok(palestranteService.atualizar(id, palestrante));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar palestrante", description = "Remove um palestrante do sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Palestrante deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Palestrante não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        palestranteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
