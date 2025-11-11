package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.BusinessException;
import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Inscricao;
import br.pucpr.conexa.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public List<Inscricao> listarTodos() {
        return inscricaoRepository.findAll();
    }

    public Inscricao buscarPorId(Long id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao", "id", id));
    }

    public List<Inscricao> buscarPorUsuario(Long usuarioId) {
        return inscricaoRepository.findByUsuarioId(usuarioId);
    }

    public List<Inscricao> buscarPorEvento(Long eventoId) {
        return inscricaoRepository.findByEventoId(eventoId);
    }

    @Transactional
    public Inscricao criar(Inscricao inscricao) {
        if (inscricao.getUsuario() == null || inscricao.getEvento() == null) {
            throw new BusinessException("DADOS_INVALIDOS", "Usuário e Evento são obrigatórios");
        }
        return inscricaoRepository.save(inscricao);
    }

    @Transactional
    public Inscricao atualizar(Long id, Inscricao inscricaoAtualizada) {
        Inscricao inscricao = buscarPorId(id);
        inscricao.setStatus(inscricaoAtualizada.getStatus());
        inscricao.setObservacoes(inscricaoAtualizada.getObservacoes());
        return inscricaoRepository.save(inscricao);
    }

    @Transactional
    public void deletar(Long id) {
        Inscricao inscricao = buscarPorId(id);
        inscricaoRepository.delete(inscricao);
    }
}
