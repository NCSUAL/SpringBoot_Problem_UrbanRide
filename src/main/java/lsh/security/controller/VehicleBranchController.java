package lsh.security.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lsh.security.common.validator.crud.Delete;
import lsh.security.common.validator.crud.Update;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.vehicle.VehicleWithBranchRequest;
import lsh.security.dto.response.VehicleResponse;
import lsh.security.service.VehicleBranchService;
import lsh.security.swagger.controller.VehicleBranchControllerSwagger;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vehicle-branch")
public class VehicleBranchController implements VehicleBranchControllerSwagger{

    private final VehicleBranchService vehicleBranchService;

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ApiEntity<VehicleResponse> requestUpdateAssociationVehicleAndBranch(@Validated(Update.class) @RequestBody VehicleWithBranchRequest vehicleWithBranchRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleBranchService.associationVehicleAndBranch(vehicleWithBranchRequest)));
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    public ApiEntity<VehicleResponse> requestDeleteAssociationVehicleAndBranch(@Validated(Delete.class) @RequestBody VehicleWithBranchRequest vehicleWithBranchRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleBranchService.deleteAssociationVehicleAndBranch(vehicleWithBranchRequest)));
    }

}
