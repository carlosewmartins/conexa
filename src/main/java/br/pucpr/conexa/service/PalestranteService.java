package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Palestrante;
import br.pucpr.conexa.repository.PalestranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PalestranteService {

    @Autowired
    private PalestranteRepository palestranteRepository;

    public List<Palestrante> listarTodos() {
        return palestranteRepository.findAll();
    }

    public Palestrante buscarPorId(Long id) {
        return palestranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Palestrante", "id", id));
    }

    @Transactional
    public Palestrante criar(Palestrante palestrante) {
        return palestranteRepository.save(palestrante);
    }

    @Transactional
    public Palestrante atualizar(Long id, Palestrante palestranteAtualizado) {
        Palestrante palestrante = buscarPorId(id);
        palestrante.setNome(palestranteAtualizado.getNome());
        palestrante.setEmail(palestranteAtualizado.getEmail());
        palestrante.setTelefone(palestranteAtualizado.getTelefone());
        palestrante.setBiografia(palestranteAtualizado.getBiografia());
        palestrante.setEspecialidade(palestranteAtualizado.getEspecialidade());
        palestrante.setEmpresa(palestranteAtualizado.getEmpresa());
        palestrante.setLinkedin(palestranteAtualizado.getLinkedin());
        return palestranteRepository.save(palestrante);
    }

    @Transactional
    public void deletar(Long id) {
        Palestrante palestrante = buscarPorId(id);
        palestranteRepository.delete(palestrante);
    }
}
