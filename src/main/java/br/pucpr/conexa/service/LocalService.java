package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Local;
import br.pucpr.conexa.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public List<Local> listarTodos() {
        return localRepository.findAll();
    }

    public Local buscarPorId(Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local", "id", id));
    }

    @Transactional
    public Local criar(Local local) {
        return localRepository.save(local);
    }

    @Transactional
    public Local atualizar(Long id, Local localAtualizado) {
        Local local = buscarPorId(id);
        local.setNome(localAtualizado.getNome());
        local.setEndereco(localAtualizado.getEndereco());
        local.setCidade(localAtualizado.getCidade());
        local.setEstado(localAtualizado.getEstado());
        local.setCapacidade(localAtualizado.getCapacidade());
        return localRepository.save(local);
    }

    @Transactional
    public void deletar(Long id) {
        Local local = buscarPorId(id);
        localRepository.delete(local);
    }
}
