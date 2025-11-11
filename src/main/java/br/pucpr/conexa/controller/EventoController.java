package br.pucpr.conexa.controller;

import br.pucpr.conexa.repository.EventoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Endpoints para gerenciamento de eventos")
@SecurityRequirement(name = "bearerAuth")
public class EventoController {
    @Autowired private EventoRepository repository;
}