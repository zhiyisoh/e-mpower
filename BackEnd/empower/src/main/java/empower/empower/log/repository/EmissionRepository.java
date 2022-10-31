package empower.empower.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.log.entity.Emissions;

public interface EmissionRepository extends JpaRepository<Emissions, Long>{
    Emissions findByItemName(String itemName);
}
