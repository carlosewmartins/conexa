package br.pucpr.conexa.service;

import br.pucpr.conexa.exception.BusinessException;
import br.pucpr.conexa.exception.ResourceNotFoundException;
import br.pucpr.conexa.model.Categoria;
import br.pucpr.conexa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
    }

    @Transactional
    public Categoria criar(Categoria categoria) {
        if (categoriaRepository.findByNome(categoria.getNome()).isPresent()) {
            throw new BusinessException("CATEGORIA_DUPLICADA", "Já existe uma categoria com este nome");
        }
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        Categoria categoria = buscarPorId(id);

        if (!categoria.getNome().equals(categoriaAtualizada.getNome()) &&
            categoriaRepository.findByNome(categoriaAtualizada.getNome()).isPresent()) {
            throw new BusinessException("CATEGORIA_DUPLICADA", "Já existe uma categoria com este nome");
        }

        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}
