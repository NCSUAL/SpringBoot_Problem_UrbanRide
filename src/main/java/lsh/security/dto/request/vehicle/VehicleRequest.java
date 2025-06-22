package lsh.security.dto.request.vehicle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lsh.security.common.validator.crud.Create;
import lsh.security.constant.nested.VehicleType;
import lsh.security.domain.Vehicle;

public record VehicleRequest(
    @NotNull(message = "값을 포함시켜야 합니다.", groups = {Create.class})
    @NotEmpty(message = "빈 값이 아니어야 합니다.", groups = {Create.class})
    String vin,
    @NotNull(message = "값을 포함시켜야 합니다.")
    VehicleType vehicleType
) {

    public Vehicle toEntity(){
        return Vehicle.builder()
        .id(vin)
        .vehicleType(vehicleType)
        .build();
    }
} 
