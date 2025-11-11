package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.BusinessException;
import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Avaliacao;
import br.pucpr.conexa.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliacao", "id", id));
    }

    public List<Avaliacao> buscarPorUsuario(Long usuarioId) {
        return avaliacaoRepository.findByUsuarioId(usuarioId);
    }

    public List<Avaliacao> buscarPorEvento(Long eventoId) {
        return avaliacaoRepository.findByEventoId(eventoId);
    }

    @Transactional
    public Avaliacao criar(Avaliacao avaliacao) {
        if (avaliacao.getUsuario() == null || avaliacao.getEvento() == null) {
            throw new BusinessException("DADOS_INVALIDOS", "Usuário e Evento são obrigatórios");
        }
        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public Avaliacao atualizar(Long id, Avaliacao avaliacaoAtualizada) {
        Avaliacao avaliacao = buscarPorId(id);
        avaliacao.setNota(avaliacaoAtualizada.getNota());
        avaliacao.setComentario(avaliacaoAtualizada.getComentario());
        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public void deletar(Long id) {
        Avaliacao avaliacao = buscarPorId(id);
        avaliacaoRepository.delete(avaliacao);
    }
}
