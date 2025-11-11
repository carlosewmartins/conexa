package br.pucpr.conexa.controller;

import br.pucpr.conexa.model.Inscricao;
import br.pucpr.conexa.service.InscricaoService;
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
@RequestMapping("/api/inscricoes")
@Tag(name = "Inscrições", description = "Endpoints para gerenciamento de inscrições em eventos")
@SecurityRequirement(name = "bearerAuth")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping
    @Operation(summary = "Listar todas as inscrições", description = "Retorna uma lista com todas as inscrições cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de inscrições retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    public ResponseEntity<List<Inscricao>> listarTodos() {
        return ResponseEntity.ok(inscricaoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar inscrição por ID", description = "Retorna uma inscrição específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscrição encontrada"),
        @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    })
    public ResponseEntity<Inscricao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inscricaoService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar inscrições por usuário", description = "Retorna todas as inscrições de um usuário")
    public ResponseEntity<List<Inscricao>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(inscricaoService.buscarPorUsuario(usuarioId));
    }

    @GetMapping("/evento/{eventoId}")
    @Operation(summary = "Buscar inscrições por evento", description = "Retorna todas as inscrições de um evento")
    public ResponseEntity<List<Inscricao>> buscarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(inscricaoService.buscarPorEvento(eventoId));
    }

    @PostMapping
    @Operation(summary = "Criar nova inscrição", description = "Cria uma nova inscrição em um evento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inscrição criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Inscricao> criar(@Valid @RequestBody Inscricao inscricao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criar(inscricao));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar inscrição", description = "Atualiza os dados de uma inscrição existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscrição atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    })
    public ResponseEntity<Inscricao> atualizar(@PathVariable Long id, @Valid @RequestBody Inscricao inscricao) {
        return ResponseEntity.ok(inscricaoService.atualizar(id, inscricao));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar inscrição", description = "Remove uma inscrição do sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Inscrição deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Inscrição não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        inscricaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
