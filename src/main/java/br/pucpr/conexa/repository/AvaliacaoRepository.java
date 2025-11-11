package br.pucpr.conexa.repository;

import br.pucpr.conexa.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByUsuarioId(Long usuarioId);
    List<Avaliacao> findByEventoId(Long eventoId);
}
