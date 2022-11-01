package empower.empower.log.service;
import empower.empower.log.entity.Emissions;
import empower.empower.log.repository.EmissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmissionsService {
    @Autowired
    private EmissionsRepository emRepository;
    
    public List<Emissions> listAllEmissions(){
        return emRepository.findAll();
    }

    
}
