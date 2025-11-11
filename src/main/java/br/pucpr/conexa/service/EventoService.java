package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Evento;
import br.pucpr.conexa.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento", "id", id));
    }

    @Transactional
    public Evento criar(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento atualizar(Long id, Evento eventoAtualizado) {
        Evento evento = buscarPorId(id);
        evento.setNome(eventoAtualizado.getNome());
        evento.setData(eventoAtualizado.getData());
        evento.setDescricao(eventoAtualizado.getDescricao());
        evento.setCategoria(eventoAtualizado.getCategoria());
        evento.setLocal(eventoAtualizado.getLocal());
        evento.setOrganizador(eventoAtualizado.getOrganizador());
        return eventoRepository.save(evento);
    }

    @Transactional
    public void deletar(Long id) {
        Evento evento = buscarPorId(id);
        eventoRepository.delete(evento);
    }
}
