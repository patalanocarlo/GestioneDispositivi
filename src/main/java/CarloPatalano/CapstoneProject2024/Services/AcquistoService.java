package CarloPatalano.CapstoneProject2024.Services;



import CarloPatalano.CapstoneProject2024.Entities.Acquisto;
import CarloPatalano.CapstoneProject2024.Repository.AcquistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository acquistoRepository;

    public List<Acquisto> getAcquistiByUserId(Long userId) {
        return acquistoRepository.findByUserId(userId);
    }
}
