package CarloPatalano.GestioneDispositivi.Services;


import CarloPatalano.GestioneDispositivi.DTO.UnauthorizedException;
import CarloPatalano.GestioneDispositivi.DTO.UserLoginDTO;
import CarloPatalano.GestioneDispositivi.DTO.UserRegistrationDTO;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {

        Dipendente dipendente = dipendenteService.findByEmail(payload.getEmail());

        if (dipendente != null && dipendente.getPassword().equals(payload.getPassword())) {
            return jwtTools.createToken(dipendente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }

    public String registerUser(UserRegistrationDTO registrationDTO) {
        Dipendente newDipendente = new Dipendente(
                registrationDTO.getEmail(),
                registrationDTO.getCognome(),
                registrationDTO.getName(),
                registrationDTO.getUsername(),
                registrationDTO.getPassword()
        );

        Dipendente savedDipendente = dipendenteService.save(newDipendente);
        return jwtTools.createToken(savedDipendente);
    }
}