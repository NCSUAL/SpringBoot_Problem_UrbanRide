package lsh.security.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lsh.security.common.validator.crud.Create;
import lsh.security.common.validator.crud.Update;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.vehicle.VehicleRequest;
import lsh.security.dto.request.vehicle.VehicleWithBranchRequest;
import lsh.security.dto.response.VehicleResponse;
import lsh.security.service.VehicleBranchService;
import lsh.security.service.VehicleService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleBranchService vehicleBranchService;

    @RequestMapping(method = RequestMethod.GET)
    public ApiEntity<List<VehicleResponse>> requestInquiryAllVehicle(){
        return ApiEntity.ok(
            vehicleService
            .inquiryAll()
            .stream()
            .map(arg -> VehicleResponse.of(arg))
            .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiEntity<VehicleResponse> requestInsertVehicle(@Validated(Create.class) @RequestBody VehicleRequest vehicleRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleService.insertVehicle(vehicleRequest)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiEntity<VehicleResponse> requestAssociationVehicleAndBranch(@Validated(Update.class) @RequestBody VehicleWithBranchRequest vehicleWithBranchRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleBranchService.associationVehicleAndBranch(vehicleWithBranchRequest)));
    }
}
