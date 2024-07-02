package CarloPatalano.GestioneDispositivi.Controllers;


import CarloPatalano.GestioneDispositivi.DTO.DipendenteresponseDTO;
import CarloPatalano.GestioneDispositivi.DTO.NewUserDTO;
import CarloPatalano.GestioneDispositivi.DTO.NotFoundException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Entities.Role;
import CarloPatalano.GestioneDispositivi.Services.DipendenteService;
import CarloPatalano.GestioneDispositivi.Tools.JWTTools;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private Cloudinary cloudinary;
@Autowired
private JWTTools jwtTools;
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.getAllDipendenti();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendente getDipendenteById(@PathVariable Long id) {
        return dipendenteService.getDipendenteById(id);
    }

    @PostMapping("/dipendenti")
    @PreAuthorize("hasAuthority('ADMIN')") // Assicurati che solo gli admin possano chiamare questo endpoint
    public ResponseEntity<DipendenteresponseDTO> createAdmin(@RequestBody @Validated NewUserDTO newUserDTO) {
        // Creare un dipendente con il ruolo ADMIN
        Dipendente dipendente = new Dipendente(newUserDTO.getEmail(), newUserDTO.getPassword(), newUserDTO.getNome(), newUserDTO.getCognome(), Role.ADMIN);
        Dipendente createdDipendente = dipendenteService.createDipendente(dipendente);
        String token = jwtTools.generateToken(createdDipendente);
        return ResponseEntity.ok(new DipendenteresponseDTO(token));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Dipendente updateDipendente(@PathVariable Long id, @Validated @RequestBody Dipendente dipendenteDetails) {
        return dipendenteService.updateDipendente(id, dipendenteDetails);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
    }

    @PutMapping("/{id}/avatar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendente updateAvatar(@PathVariable Long id, @RequestParam("avatar") MultipartFile file) throws IOException {
        Dipendente dipendente = dipendenteService.getDipendenteById(id);
        if (dipendente == null) {
            throw new NotFoundException("Dipendente non trovato con id: " + id);
        }
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        dipendente.setAvatar(imageUrl);
        Dipendente updatedDipendente = dipendenteService.updateDipendente(id, dipendente);
        return updatedDipendente;
    }
}