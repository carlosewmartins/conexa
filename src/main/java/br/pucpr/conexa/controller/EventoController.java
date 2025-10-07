package br.pucpr.conexa.controller;

import br.pucpr.conexa.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
    @Autowired private EventoRepository repository;
}