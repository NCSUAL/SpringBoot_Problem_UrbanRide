package lsh.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lsh.security.domain.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, String>{
    Optional<Vehicle> findById(String id);
}
