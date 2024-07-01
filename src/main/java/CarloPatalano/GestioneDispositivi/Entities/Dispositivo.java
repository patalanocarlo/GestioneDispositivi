package CarloPatalano.GestioneDispositivi.Entities;




import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La presenza di un tipo di dispositivo è necessaria")
    private String tipo;

    @NotBlank (message ="La presenza dello stato di un dispositivo è necessaria")
    private String stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Dispositivo(String tipo, String stato, Dipendente dipendente) {
        this.tipo = tipo;
        this.stato = stato;
        this.dipendente = dipendente;
    }
}
