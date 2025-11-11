package br.pucpr.conexa.controller;

import br.pucpr.conexa.model.Usuario;
import br.pucpr.conexa.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados (requer permissão de ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso negado - usuário não possui permissão de ADMIN")
    })
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}