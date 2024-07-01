package CarloPatalano.GestioneDispositivi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Devi inserire uno username")
    @Size(min = 3, max = 10, message = "Lo username scelto deve avere minimo 3 caratteri e massimo 10")
    private String username;

    @NotBlank(message = "Il nome è sempre richiesto")
    private String name;

    @NotBlank(message = "Il cognome è sempre richiesto")
    private String cognome;

    @Email(message = "Email non valida.")
    @NotBlank(message = "Inserisci una email nel campo.")
    private String email;

    private String avatar;
    private String password;

    public Dipendente(String avatar, String email, String cognome, String name, String username) {
        this.avatar = avatar;
        this.email = email;
        this.cognome = cognome;
        this.name = name;
        this.username = username;
    }
}