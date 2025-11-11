package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.BusinessException;
import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Sessao;
import br.pucpr.conexa.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    public List<Sessao> listarTodos() {
        return sessaoRepository.findAll();
    }

    public Sessao buscarPorId(Long id) {
        return sessaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sessao", "id", id));
    }

    public List<Sessao> buscarPorEvento(Long eventoId) {
        return sessaoRepository.findByEventoId(eventoId);
    }

    public List<Sessao> buscarPorPalestrante(Long palestranteId) {
        return sessaoRepository.findByPalestranteId(palestranteId);
    }

    @Transactional
    public Sessao criar(Sessao sessao) {
        if (sessao.getEvento() == null) {
            throw new BusinessException("DADOS_INVALIDOS", "Evento é obrigatório");
        }
        if (sessao.getHoraInicio().isAfter(sessao.getHoraFim())) {
            throw new BusinessException("HORARIO_INVALIDO", "Hora de início deve ser antes da hora de término");
        }
        return sessaoRepository.save(sessao);
    }

    @Transactional
    public Sessao atualizar(Long id, Sessao sessaoAtualizada) {
        Sessao sessao = buscarPorId(id);

        if (sessaoAtualizada.getHoraInicio().isAfter(sessaoAtualizada.getHoraFim())) {
            throw new BusinessException("HORARIO_INVALIDO", "Hora de início deve ser antes da hora de término");
        }

        sessao.setTitulo(sessaoAtualizada.getTitulo());
        sessao.setDescricao(sessaoAtualizada.getDescricao());
        sessao.setHoraInicio(sessaoAtualizada.getHoraInicio());
        sessao.setHoraFim(sessaoAtualizada.getHoraFim());
        sessao.setEvento(sessaoAtualizada.getEvento());
        sessao.setPalestrante(sessaoAtualizada.getPalestrante());
        sessao.setLocal(sessaoAtualizada.getLocal());
        sessao.setCapacidade(sessaoAtualizada.getCapacidade());
        sessao.setTipo(sessaoAtualizada.getTipo());

        return sessaoRepository.save(sessao);
    }

    @Transactional
    public void deletar(Long id) {
        Sessao sessao = buscarPorId(id);
        sessaoRepository.delete(sessao);
    }
}
