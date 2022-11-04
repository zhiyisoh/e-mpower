package empower.empower.bin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.bin.entity.Bin;
import org.springframework.stereotype.Repository;


@Repository
public interface BinRepository extends JpaRepository <Bin, Long>{
    List<Bin> findById(long id);
    List<Bin> findByPostalCode(int postalCode);
    Optional<Bin> findByAddress(String address);
    List<Bin> findByIct(boolean ict); //information and communication equipment
    List<Bin> findByBattery(boolean battery);
    List<Bin> findByLamp(boolean Lamp);
    Optional<Bin> findByLatitude(double latitude);
    Optional<Bin> findByLongitude(double longitude);

}
