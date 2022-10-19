package empower.empower.log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.log.entity.Emissions;

public interface EmissionRepository extends JpaRepository<Emissions, Long>{
    Optional<Emissions> findByItemName(String itemName);
}
