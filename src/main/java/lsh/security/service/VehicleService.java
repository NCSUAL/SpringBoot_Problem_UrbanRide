package lsh.security.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lsh.security.domain.Vehicle;
import lsh.security.dto.request.vehicle.VehicleRequest;
import lsh.security.exception.NotFoundEntityException;
import lsh.security.repository.VehicleRepository;
import static lsh.security.repository.specification.VehicleSpecification.*;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle insertVehicle(VehicleRequest vehicleRequest){
        return vehicleRepository.save(vehicleRequest.toEntity());
    }

    public Vehicle insertVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> inquiryWithID(final String id){
        return vehicleRepository.findById(id);
    }

    public Vehicle patchByVehicleRequest( final VehicleRequest vehicleRequest){
        return isPresent(vehicleRequest.vin(), present -> present.patch(vehicleRequest));
    }

    public Vehicle updateByVehicleRequest(final VehicleRequest vehicleRequest){
        return isPresent(vehicleRequest.vin(), present -> present.update(vehicleRequest));
    }

    public Vehicle isPresent(final String id){
        return inquiryWithID(id).orElseThrow( () -> new NotFoundEntityException(HttpStatus.BAD_REQUEST, "해당 엔티티는 존재하지 않습니다."));
    }

    public Vehicle isPresent(final String id, Consumer<Vehicle> consumer){
        Optional<Vehicle> vehicle = inquiryWithID(id);
        vehicle.ifPresentOrElse(consumer, () -> new NotFoundEntityException(HttpStatus.BAD_REQUEST, "해당 엔티티는 존재하지 않습니다."));
        return vehicle.get();
    }

    public List<Vehicle> inquiryAll(){
        return vehicleRepository.findAll();
    }

    public List<Vehicle> inquiryAllByAssociationBranch(){
        return vehicleRepository.findAll(associationBranch());
    }

    public void delete(final String id){
        vehicleRepository.deleteById(id);
    }

}
