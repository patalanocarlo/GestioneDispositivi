package CarloPatalano.CapstoneProject2024.Services;

import CarloPatalano.CapstoneProject2024.Entities.Utente;
import CarloPatalano.CapstoneProject2024.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente saveUtente(Utente utente) {
        if (utenteRepository.findByEmail(utente.getEmail()).isPresent()) {
            throw new RuntimeException("Email già in uso");
        }
        if (utenteRepository.findByUsername(utente.getUsername()).isPresent()) {
            throw new RuntimeException("Username già in uso");
        }
        utente.setPassword(utente.getPassword());
        if (utente.getProfileImage() == null) {
            utente.setProfileImage("default-image-url");
        }
        return utenteRepository.save(utente);
    }

    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Optional<Utente> findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }
}
