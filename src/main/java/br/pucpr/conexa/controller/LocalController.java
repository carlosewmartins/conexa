package br.pucpr.conexa.controller;

import br.pucpr.conexa.model.Local;
import br.pucpr.conexa.service.LocalService;
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
@RequestMapping("/api/locais")
@Tag(name = "Locais", description = "Endpoints para gerenciamento de locais")
@SecurityRequirement(name = "bearerAuth")
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping
    @Operation(summary = "Listar todos os locais", description = "Retorna uma lista com todos os locais cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de locais retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    public ResponseEntity<List<Local>> listarTodos() {
        return ResponseEntity.ok(localService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar local por ID", description = "Retorna um local específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Local encontrado"),
        @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    public ResponseEntity<Local> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(localService.buscarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar novo local", description = "Cria um novo local no sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Local criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Local> criar(@Valid @RequestBody Local local) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localService.criar(local));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar local", description = "Atualiza os dados de um local existente (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Local atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Local não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Local> atualizar(@PathVariable Long id, @Valid @RequestBody Local local) {
        return ResponseEntity.ok(localService.atualizar(id, local));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar local", description = "Remove um local do sistema (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Local deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Local não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        localService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
