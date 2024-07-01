package CarloPatalano.GestioneDispositivi.Controllers;




import CarloPatalano.GestioneDispositivi.Entities.Dispositivo;
import CarloPatalano.GestioneDispositivi.Services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Dispositivo")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping
    public List<Dispositivo> getAllDispositivi() {
        return dispositivoService.getAllDispositivi();
    }
    @GetMapping("/{id}")
    public Dispositivo getDispositivoById(@PathVariable Long id) {
        return dispositivoService.getDispositivoById(id);
    }

    @PostMapping
    public Dispositivo createDispositivo( @RequestBody @Validated Dispositivo dispositivo) {
        return dispositivoService.createDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    public Dispositivo updateDispositivo(@PathVariable Long id, @RequestBody @Validated Dispositivo dispositivoDetails) {
        return dispositivoService.updateDispositivo(id, dispositivoDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDispositivo(@PathVariable Long id) {
        dispositivoService.deleteDispositivo(id);
    }

    @PostMapping("/{idDipendente}/{assegnaid}")
    public Dispositivo assegnaDispositivo(@PathVariable Long idDipendente, @RequestBody @Validated Dispositivo dispositivo) {
        return dispositivoService.assegnaDispositivo(idDipendente, dispositivo);
    }
}
