package CarloPatalano.CapstoneProject2024.Services;
import CarloPatalano.CapstoneProject2024.Entities.UtenteRuolo;
import CarloPatalano.CapstoneProject2024.Repository.UtenteRuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteRuoloService {

    @Autowired
    private UtenteRuoloRepository utenteRuoloRepository;

    public UtenteRuolo saveUtenteRuolo(UtenteRuolo utenteRuolo) {
        return utenteRuoloRepository.save(utenteRuolo);
    }

    public Optional<UtenteRuolo> getUtenteRuoloById(Long id) {
        return utenteRuoloRepository.findById(id);
    }

    public Optional<UtenteRuolo> findByRuolo(String ruolo) {
        return utenteRuoloRepository.findByRuolo(ruolo);
    }
}
