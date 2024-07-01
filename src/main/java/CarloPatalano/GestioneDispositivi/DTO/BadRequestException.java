package CarloPatalano.GestioneDispositivi.DTO;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private final List<ObjectError> errorsList;

    public BadRequestException(String message) {
        super(message);
        this.errorsList = null;
    }

    public BadRequestException(List<ObjectError> errorsList) {
        super("Errore di validazione");
        this.errorsList = errorsList;
    }

    public List<ObjectError> getErrorsList() {
        return errorsList;
    }
}