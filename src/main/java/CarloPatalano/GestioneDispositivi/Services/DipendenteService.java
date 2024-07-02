package CarloPatalano.GestioneDispositivi.Services;




import CarloPatalano.GestioneDispositivi.DTO.DipendenteRegisterDTO;
import CarloPatalano.GestioneDispositivi.DTO.NewUserDTO;
import CarloPatalano.GestioneDispositivi.DTO.NotFoundException;
import CarloPatalano.GestioneDispositivi.DTO.UnauthorizedException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Entities.Role;
import CarloPatalano.GestioneDispositivi.Repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Dipendente> getAllDipendenti() {
        List<Dipendente> dipendenti = dipendenteRepository.findAll();
        return dipendenti;
    }
    public Long saveNewDipendente(NewUserDTO newUserDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setEmail(newUserDTO.getEmail());
        dipendente.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        dipendente.setName(newUserDTO.getNome());
        dipendente.setCognome(newUserDTO.getCognome());
        dipendente.setRole(Role.USER);
        Dipendente savedDipendente = dipendenteRepository.save(dipendente);

        return savedDipendente.getId();
    }

    public Dipendente authenticate(String email, String password) {
        Dipendente dipendente = dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("Credenziali non valide"));

        if (passwordEncoder.matches(password, dipendente.getPassword())) {
            return dipendente;
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }

    public Dipendente getDipendenteById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
    }
    public Dipendente createDipendente(Dipendente dipendente) {
dipendente.setPassword(passwordEncoder.encode(dipendente.getPassword()));
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(Long id, Dipendente dipendenteDetails) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + id));
        dipendente.setUsername(dipendenteDetails.getUsername());
        dipendente.setName(dipendenteDetails.getName());
        dipendente.setCognome(dipendenteDetails.getCognome());
        dipendente.setEmail(dipendenteDetails.getEmail());
        if(dipendenteDetails.getPassword() != null ){
            dipendente.setPassword(passwordEncoder.encode(dipendente.getPassword()));
        }
        return dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + id));

        dipendenteRepository.delete(dipendente);
    }
    public Dipendente findByEmail(String email){
        return dipendenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Dipendentecon email " + email + " non trovato!"));
    }

    public Dipendente registerNewDipendente(DipendenteRegisterDTO registerDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setEmail(registerDTO.email());
        dipendente.setPassword(passwordEncoder.encode(registerDTO.password()));
        dipendente.setRole(Role.ADMIN); // Imposta il ruolo a ADMIN

        return dipendenteRepository.save(dipendente);
    }
    public Dipendente save(Dipendente adminDetails) {
        return dipendenteRepository.save(adminDetails);
    }
}
