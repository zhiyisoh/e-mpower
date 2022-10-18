package empower.empower.bin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.bin.entity.Bin;

public interface BinRepository extends JpaRepository <Bin, Long>{
    List<Bin> findById(long id);
    Optional<Bin> findByPostalCode(int postalCode);
    Optional<Bin> findByAddress(String address);
    Optional<Bin> findByIct(boolean ict); //information and communication equipment
    Optional<Bin> findByBattery(boolean battery);
    Optional<Bin> findByLamp(boolean Lamp);
    Optional<Bin> findByLatitude(float latitude);
    Optional<Bin> findByLongitude(float longitude);

}
