package CarloPatalano.GestioneDispositivi.Services;

import CarloPatalano.GestioneDispositivi.DTO.DipendenteLoginDTO;
import CarloPatalano.GestioneDispositivi.DTO.NewUserDTO;
import CarloPatalano.GestioneDispositivi.DTO.UnauthorizedException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Entities.Role;
import CarloPatalano.GestioneDispositivi.Tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(DipendenteLoginDTO payload){
        Dipendente dipendente=this.dipendenteService.findByEmail(payload.email());
        if(dipendente.getPassword().equals(payload.password())){
            return jwtTools.generateToken(dipendente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
    public void createAdmin(NewUserDTO newUserDTO) {
        Dipendente admin = new Dipendente();
        admin.setEmail(newUserDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        admin.setRole(Role.ADMIN);

        dipendenteService.createDipendente(admin);
    }

}