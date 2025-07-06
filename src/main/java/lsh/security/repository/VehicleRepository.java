package lsh.security.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import lsh.security.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String>, JpaSpecificationExecutor<Vehicle> {
    Optional<Vehicle> findById(String id);
}
