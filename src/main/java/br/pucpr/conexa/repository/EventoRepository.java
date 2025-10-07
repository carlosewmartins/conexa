package br.pucpr.conexa.repository;
import br.pucpr.conexa.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EventoRepository extends JpaRepository<Evento, Long> {}