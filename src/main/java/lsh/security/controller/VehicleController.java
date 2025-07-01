package lsh.security.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lsh.security.common.validator.crud.Create;
import lsh.security.common.validator.crud.Patch;
import lsh.security.common.validator.crud.Update;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.vehicle.VehicleRequest;
import lsh.security.dto.response.VehicleResponse;
import lsh.security.service.VehicleService;
import lsh.security.swagger.controller.VehicleControllerSwagger;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vehicle")
public class VehicleController implements VehicleControllerSwagger{
    private final VehicleService vehicleService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ApiEntity<List<VehicleResponse>> requestInquiryAllVehicle(){
        return ApiEntity.ok(
            vehicleService
            .inquiryAll()
            .stream()
            .map(arg -> VehicleResponse.of(arg))
            .collect(Collectors.toList()));
    }
    
    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ApiEntity<VehicleResponse> requestInsertVehicle(@Validated(Create.class) @RequestBody VehicleRequest vehicleRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleService.insertVehicle(vehicleRequest)));
    }
    
    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ApiEntity<VehicleResponse> requestUpdateVehicle(@Validated(Update.class) @RequestBody VehicleRequest vehicleRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleService.updateByVehicleRequest(vehicleRequest)));
    }

    
    @Override
    @RequestMapping(method = RequestMethod.PATCH)
    public ApiEntity<VehicleResponse> requestPatchVehicle(@Validated(Patch.class) @RequestBody VehicleRequest vehicleRequest){
        return ApiEntity.ok(VehicleResponse.of(vehicleService.patchByVehicleRequest(vehicleRequest)));
    }

    
    @Override
    @RequestMapping(value = "/{vin}",method = RequestMethod.DELETE)
    public ApiEntity<String> requestDeleteVehicle(@PathVariable(name = "vin") String vin){
        vehicleService.isPresent(vin, present -> vehicleService.delete(vin));
        return ApiEntity.ok("해당 엔티티를 삭제했습니다");
    }

}
