package CarloPatalano.GestioneDispositivi.DTO;
public record UserLoginDTO(String email, String password) {

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}