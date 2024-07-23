package CarloPatalano.CapstoneProject2024.Repository;


import CarloPatalano.CapstoneProject2024.Entities.UtenteRuolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRuoloRepository extends JpaRepository<UtenteRuolo, Long> {
    Optional<UtenteRuolo> findByRuolo(String ruolo);
}
