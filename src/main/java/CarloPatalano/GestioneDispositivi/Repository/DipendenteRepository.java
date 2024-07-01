package CarloPatalano.GestioneDispositivi.Repository;

import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);

    boolean existsByEmail(String email);
}
