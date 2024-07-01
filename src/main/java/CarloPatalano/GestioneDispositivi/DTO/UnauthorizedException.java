package CarloPatalano.GestioneDispositivi.DTO;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}