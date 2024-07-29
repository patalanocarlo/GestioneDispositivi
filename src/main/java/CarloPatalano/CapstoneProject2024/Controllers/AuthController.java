package CarloPatalano.CapstoneProject2024.Controllers;

import CarloPatalano.CapstoneProject2024.Configuration.JWTTools;
import CarloPatalano.CapstoneProject2024.Entities.Utente;
import CarloPatalano.CapstoneProject2024.Entities.UtenteRuolo;
import CarloPatalano.CapstoneProject2024.Payload.LoginRequestPayload;
import CarloPatalano.CapstoneProject2024.Payload.RegisterRequestPayload;
import CarloPatalano.CapstoneProject2024.Services.UtenteService;
import CarloPatalano.CapstoneProject2024.Services.UtenteRuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteRuoloService utenteRuoloService;

    @Autowired
    private JWTTools jwtTools;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestPayload registerRequestPayload) {
        UtenteRuolo utenteRuolo = utenteRuoloService.getUtenteRuoloById(registerRequestPayload.getRuoloId())
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
        Utente utente = new Utente();
        utente.setNome(registerRequestPayload.getNome());
        utente.setCognome(registerRequestPayload.getCognome());
        utente.setUsername(registerRequestPayload.getUsername());
        utente.setEmail(registerRequestPayload.getEmail());
        utente.setPassword(registerRequestPayload.getPassword());
        utente.setUtenteRuolo(utenteRuolo);

        try {
            utenteService.saveUtente(utente);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        return "Registrazione completata";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestPayload loginRequestPayload) {
        Utente utente = utenteService.findByUsername(loginRequestPayload.getUsername())
                .orElseGet(() -> utenteService.findByEmail(loginRequestPayload.getUsername())
                        .orElseThrow(() -> new RuntimeException("Utente non trovato")));

        if (loginRequestPayload.getPassword().equals(utente.getPassword())) {
            String token = jwtTools.createToken(utente);
            return token;
        } else {
            throw new RuntimeException("Credenziali non valide");
        }
    }

}
