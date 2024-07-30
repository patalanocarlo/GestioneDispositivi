package CarloPatalano.CapstoneProject2024.Controllers;



import CarloPatalano.CapstoneProject2024.Entities.Acquisto;
import CarloPatalano.CapstoneProject2024.Services.AcquistoService;
import CarloPatalano.CapstoneProject2024.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class PurchaseController {

    @Autowired
    private AcquistoService acquistoService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/purchases")
    public List<Acquisto> getUserPurchases(Principal principal) {
        Long userId = utenteService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"))
                .getId();
        return acquistoService.getAcquistiByUserId(userId);
    }
}

