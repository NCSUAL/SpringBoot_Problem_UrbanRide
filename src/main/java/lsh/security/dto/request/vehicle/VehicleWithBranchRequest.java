package lsh.security.dto.request.vehicle;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lsh.security.common.validator.crud.Patch;
import lsh.security.common.validator.crud.Update;

public record VehicleWithBranchRequest(
    @NotNull(message = "값을 포함시켜야 합니다.", groups = {Update.class})
    @NotEmpty(message = "빈 값이 아니어야 합니다.", groups = {Update.class})
    String vin,
    @NotNull(message = "값을 포함시켜야 합니다.", groups = {Update.class, Patch.class})
    Long branchId
) {
} 