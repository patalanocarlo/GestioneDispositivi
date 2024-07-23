package CarloPatalano.CapstoneProject2024.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestPayload {
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private Long ruoloId;
}
