package CarloPatalano.CapstoneProject2024.Controllers;



import CarloPatalano.CapstoneProject2024.Entities.UtenteRuolo;
import CarloPatalano.CapstoneProject2024.Services.UtenteRuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ruoli")
public class UtenteRuoloController {

    @Autowired
    private UtenteRuoloService utenteRuoloService;

    @PostMapping("/create")
    public UtenteRuolo createRuolo(@RequestBody UtenteRuolo utenteRuolo) {
        return utenteRuoloService.saveUtenteRuolo(utenteRuolo);
    }
}
