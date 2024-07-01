package CarloPatalano.GestioneDispositivi.Services;



import CarloPatalano.GestioneDispositivi.DTO.BadRequestException;
import CarloPatalano.GestioneDispositivi.DTO.NotFoundException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Entities.Dispositivo;
import CarloPatalano.GestioneDispositivi.Repository.DipendenteRepository;
import CarloPatalano.GestioneDispositivi.Repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dispositivo> getAllDispositivi() {
        return dispositivoRepository.findAll();
    }

    public Dispositivo getDispositivoById(Long id) {
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo non trovato con id " + id));
    }

    public Dispositivo createDispositivo(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo updateDispositivo(Long id, Dispositivo dispositivoDetails) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo non trovato con id " + id));

        dispositivo.setTipo(dispositivoDetails.getTipo());
        dispositivo.setStato(dispositivoDetails.getStato());

        // Se il dispositivo viene assegnato a un dipendente, aggiorna la relazione
        if (dispositivoDetails.getDipendente() != null && dispositivoDetails.getDipendente().getId() != null) {
            Dipendente dipendente = dipendenteRepository.findById(dispositivoDetails.getDipendente().getId())
                    .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + dispositivoDetails.getDipendente().getId()));

            dispositivo.setDipendente(dipendente);
        } else {
            dispositivo.setDipendente(null);
        }

        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDispositivo(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo non trovato con id " + id));

        dispositivoRepository.delete(dispositivo);
    }

    public Dispositivo assegnaDispositivo(Long idDipendente, Dispositivo dispositivo) {
        if (dispositivo.getId() == null) {
            throw new BadRequestException("ID del dispositivo non specificato");
        }

        Dipendente dipendente = dipendenteRepository.findById(idDipendente)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + idDipendente));

        Dispositivo finalDispositivo = dispositivo;
        dispositivo = dispositivoRepository.findById(dispositivo.getId())
                .orElseThrow(() -> new NotFoundException("Dispositivo non trovato con id " + finalDispositivo.getId()));

        dispositivo.setDipendente(dipendente);

        return dispositivoRepository.save(dispositivo);
    }
}
