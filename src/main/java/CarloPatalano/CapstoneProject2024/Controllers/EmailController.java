package CarloPatalano.CapstoneProject2024.Controllers;

import CarloPatalano.CapstoneProject2024.Configuration.JWTTools;
import CarloPatalano.CapstoneProject2024.Configuration.MailgunSender;
import CarloPatalano.CapstoneProject2024.Entities.Utente;
import CarloPatalano.CapstoneProject2024.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private MailgunSender mailgunSender;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    // Endpoint per inviare un'email
    @PostMapping("/send")
    public String sendEmail(@RequestBody ContactForm form, HttpServletRequest request) {
        try {

            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = jwtTools.extractUsernameFromToken(token);


                Utente utente = utenteService.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("Utente non trovato"));


                String recipientEmail = "patalanocarlo674@gmail.com";
                mailgunSender.sendEmail(recipientEmail, form.getSubject(), form.getMessage());
                return "Email inviata con successo!";
            } else {
                throw new RuntimeException("Token JWT non trovato");
            }
        } catch (Exception e) {
            return "Errore nell'invio dell'email: " + e.getMessage();
        }
    }
}


class ContactForm {
    private String subject;
    private String message;

    // Getters e setters
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
