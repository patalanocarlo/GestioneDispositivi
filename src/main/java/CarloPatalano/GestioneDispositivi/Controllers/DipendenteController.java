package CarloPatalano.GestioneDispositivi.Controllers;


import CarloPatalano.GestioneDispositivi.DTO.NotFoundException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Services.DipendenteService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.getAllDipendenti();
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable Long id) {
        return dipendenteService.getDipendenteById(id);
    }

    @PostMapping
    public Dipendente createDipendente(@RequestBody @Validated Dipendente dipendente) {
        return dipendenteService.createDipendente(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable Long id, @Validated @RequestBody Dipendente dipendenteDetails) {
        return dipendenteService.updateDipendente(id, dipendenteDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
    }
    @PutMapping("/{id}/avatar")
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