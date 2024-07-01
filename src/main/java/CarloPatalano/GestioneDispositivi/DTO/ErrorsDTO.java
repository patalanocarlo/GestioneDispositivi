package CarloPatalano.GestioneDispositivi.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorsDTO {

    private String message;
    private LocalDateTime timestamp;
}