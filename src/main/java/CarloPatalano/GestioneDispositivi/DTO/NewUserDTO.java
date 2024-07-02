package CarloPatalano.GestioneDispositivi.DTO;

import CarloPatalano.GestioneDispositivi.Entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewUserDTO {
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Role role;
    public NewUserDTO(String email, String password, String nome, String cognome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }
}
