package CarloPatalano.CapstoneProject2024.Repository;

import CarloPatalano.CapstoneProject2024.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);
}