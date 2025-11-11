package br.pucpr.conexa.repository;

import br.pucpr.conexa.model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {
    Optional<Palestrante> findByEmail(String email);
}
