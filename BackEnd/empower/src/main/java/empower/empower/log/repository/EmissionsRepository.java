package empower.empower.log.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.log.entity.Emissions;
import org.springframework.stereotype.Repository;


@Repository
public interface EmissionsRepository extends JpaRepository<Emissions, Long>{
    Optional<Emissions> findById(Long id);
    List<Emissions> findByItemName(String itemName);
}
