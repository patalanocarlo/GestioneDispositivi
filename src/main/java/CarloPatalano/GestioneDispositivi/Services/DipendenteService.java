package CarloPatalano.GestioneDispositivi.Services;




import CarloPatalano.GestioneDispositivi.DTO.NewUserDTO;
import CarloPatalano.GestioneDispositivi.DTO.NotFoundException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente> getAllDipendenti() {
        List<Dipendente> dipendenti = dipendenteRepository.findAll();
        return dipendenti;
    }
    public Long saveNewDipendente(NewUserDTO newUserDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setEmail(newUserDTO.getEmail());
        dipendente.setPassword(newUserDTO.getPassword());
        dipendente.setName(newUserDTO.getNome());
        dipendente.setCognome(newUserDTO.getCognome());
        Dipendente savedDipendente = dipendenteRepository.save(dipendente);
        return savedDipendente.getId();
    }

    public Dipendente getDipendenteById(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + id));

        return dipendente;
    }

    public Dipendente createDipendente(Dipendente dipendente) {

        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(Long id, Dipendente dipendenteDetails) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + id));

        dipendente.setUsername(dipendenteDetails.getUsername());
        dipendente.setName(dipendenteDetails.getName());
        dipendente.setCognome(dipendenteDetails.getCognome());
        dipendente.setEmail(dipendenteDetails.getEmail());


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


}
