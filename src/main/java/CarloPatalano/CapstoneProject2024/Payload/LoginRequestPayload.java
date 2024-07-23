package CarloPatalano.CapstoneProject2024.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestPayload {
    private String username;
    private String password;
}
