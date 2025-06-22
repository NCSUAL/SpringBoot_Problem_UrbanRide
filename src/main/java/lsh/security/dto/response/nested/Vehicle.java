package lsh.security.dto.response.nested;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lsh.security.constant.nested.VehicleType;

@RequiredArgsConstructor
@Builder
@Getter
public final class Vehicle {
    private final String id;
    private final VehicleType vehicleType;

    public static Vehicle of(lsh.security.domain.Vehicle vehicle){
        return Vehicle.builder()
            .id(vehicle.getId())
            .vehicleType(vehicle.getVehicleType())
            .build();
    }

}
