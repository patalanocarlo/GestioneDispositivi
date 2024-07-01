package CarloPatalano.GestioneDispositivi.Controllers;

import CarloPatalano.GestioneDispositivi.DTO.BadRequestException;
import CarloPatalano.GestioneDispositivi.DTO.DipendenteLoginDTO;
import CarloPatalano.GestioneDispositivi.DTO.DipendenteresponseDTO;
import CarloPatalano.GestioneDispositivi.DTO.NewUserDTO;
import CarloPatalano.GestioneDispositivi.Services.AuthService;
import CarloPatalano.GestioneDispositivi.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public DipendenteresponseDTO login(@RequestBody DipendenteLoginDTO payload){
        return new DipendenteresponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }


}