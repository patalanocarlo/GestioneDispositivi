package CarloPatalano.GestioneDispositivi.Controllers;


import CarloPatalano.GestioneDispositivi.DTO.DipendenteLoginDTO;
import CarloPatalano.GestioneDispositivi.DTO.DipendenteRegisterDTO;
import CarloPatalano.GestioneDispositivi.DTO.DipendenteresponseDTO;

import CarloPatalano.GestioneDispositivi.Entities.Dipendente;


import CarloPatalano.GestioneDispositivi.Services.DipendenteService;

import CarloPatalano.GestioneDispositivi.Tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    @PostMapping("/login")
    public ResponseEntity<DipendenteresponseDTO> login(@RequestBody DipendenteLoginDTO loginDTO) {
        Dipendente dipendente = dipendenteService.authenticate(loginDTO.email(), loginDTO.password());
        String token = jwtTools.generateToken(dipendente);
        return ResponseEntity.ok(new DipendenteresponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<DipendenteresponseDTO> register(@RequestBody DipendenteRegisterDTO registerDTO) {
        Dipendente dipendente = dipendenteService.registerNewDipendente(registerDTO);
        String token = jwtTools.generateToken(dipendente);
        return ResponseEntity.ok(new DipendenteresponseDTO(token));
    }
}