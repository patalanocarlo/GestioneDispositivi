package CarloPatalano.GestioneDispositivi.Configuration;


import CarloPatalano.GestioneDispositivi.DTO.UserLoginDTO;
import CarloPatalano.GestioneDispositivi.DTO.UserLoginResponseDTO;
import CarloPatalano.GestioneDispositivi.DTO.UserRegistrationDTO;
import CarloPatalano.GestioneDispositivi.Services.AuthService;
import CarloPatalano.GestioneDispositivi.Tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    public UserLoginResponseDTO register(@RequestBody UserRegistrationDTO registrationDTO) {
        System.out.println("Richiesta di registrazione ricevuta per: " + registrationDTO.toString());
        String token = authService.registerUser(registrationDTO);
        System.out.println("Token generato: " + token);
        return new UserLoginResponseDTO(token);
    }
}
