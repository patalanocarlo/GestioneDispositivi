package CarloPatalano.GestioneDispositivi.Services;

import CarloPatalano.GestioneDispositivi.DTO.DipendenteLoginDTO;
import CarloPatalano.GestioneDispositivi.DTO.UnauthorizedException;
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

    public String authenticateUserAndGenerateToken(DipendenteLoginDTO payload){
        Dipendente dipendente=this.dipendenteService.findByEmail(payload.email());
        if(dipendente.getPassword().equals(payload.password())){
            return jwtTools.createToken(dipendente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
}