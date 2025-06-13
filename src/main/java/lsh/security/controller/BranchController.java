package lsh.security.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lsh.security.common.UniqueName;
import lsh.security.controller.swagger.BranchControllerSwagger;
import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.BranchRequest;
import lsh.security.service.BranchService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/branch")
public class BranchController implements BranchControllerSwagger {

    private final BranchService branchService;

    @RequestMapping(method=RequestMethod.POST)
    @Override
    public ApiEntity<Branch> requestCreateBranch(@Valid @UniqueName @RequestBody BranchRequest branchRequest) {
        return ApiEntity.ok(branchService.createBranch(branchRequest).orElseThrow(() -> new UnsupportedOperationException("구현중입니다.")));
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public ApiEntity<List<Branch>> requestFindAllBranch(){
        return ApiEntity.ok(branchService.findAll());
    }
    
}
