package CarloPatalano.CapstoneProject2024.Repository;

import CarloPatalano.CapstoneProject2024.Entities.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {
    List<Acquisto> findByUserId(Long userId);
}