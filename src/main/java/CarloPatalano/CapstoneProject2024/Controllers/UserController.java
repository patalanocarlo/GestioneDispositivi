package CarloPatalano.CapstoneProject2024.Controllers;



import CarloPatalano.CapstoneProject2024.Entities.Utente;
import CarloPatalano.CapstoneProject2024.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/profile")
    public Utente getUserProfile(Principal principal) {
        return utenteService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}
