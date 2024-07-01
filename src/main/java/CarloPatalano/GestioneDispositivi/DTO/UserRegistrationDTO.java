package CarloPatalano.GestioneDispositivi.DTO;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String name;
    private String cognome;
    private String email;
    private String password;
}