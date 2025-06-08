package lsh.security.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.BranchRequest;
import lsh.security.service.BranchService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/branch")
public class BranchController {

    private final BranchService branchService;

    @RequestMapping(method=RequestMethod.POST)
    public ApiEntity<Branch> requestMethodName(@Valid @RequestBody BranchRequest branchRequest) {
        System.out.println(branchRequest.cityType());
        return ApiEntity.ok(branchService.createBranch(branchRequest).orElseThrow(() -> new UnsupportedOperationException("구현중입니다.")));
    }
    
}
