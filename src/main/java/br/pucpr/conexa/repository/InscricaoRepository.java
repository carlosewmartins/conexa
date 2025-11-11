package br.pucpr.conexa.repository;

import br.pucpr.conexa.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    List<Inscricao> findByUsuarioId(Long usuarioId);
    List<Inscricao> findByEventoId(Long eventoId);
}
