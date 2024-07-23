package CarloPatalano.CapstoneProject2024.Controllers;



import CarloPatalano.CapstoneProject2024.Configuration.JWTTools;
import CarloPatalano.CapstoneProject2024.Entities.Utente;
import CarloPatalano.CapstoneProject2024.Entities.UtenteRuolo;
import CarloPatalano.CapstoneProject2024.Services.UtenteService;
import CarloPatalano.CapstoneProject2024.Services.UtenteRuoloService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        UtenteRuolo utenteRuolo = utenteRuoloService.getUtenteRuoloById(registerRequest.getRuoloId())
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));

        Utente utente = new Utente();
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setUsername(registerRequest.getUsername());
        utente.setEmail(registerRequest.getEmail());
        utente.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        utente.setUtenteRuolo(utenteRuolo);

        utenteService.saveUtente(utente);


        String token = jwtTools.createToken(utente);
        return token;
    }

    @PostMapping("/login")
    public String login(@RequestBody String token) {

        jwtTools.verifyToken(token);


        String userId = jwtTools.extractIdFromToken(token);
        Long id = Long.valueOf(userId);


        Utente utente = utenteService.getUtenteById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));



        return token;
    }
}


@Getter
@Setter
class RegisterRequest {
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private Long ruoloId;;


}
@Getter
@Setter
class LoginRequest {
    private String username;
    private String password;


}
