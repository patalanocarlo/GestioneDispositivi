package CarloPatalano.GestioneDispositivi.DTO;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}