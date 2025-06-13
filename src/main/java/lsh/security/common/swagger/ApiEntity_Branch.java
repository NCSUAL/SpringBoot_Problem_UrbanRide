package lsh.security.common.swagger;

import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;

public class ApiEntity_Branch{
    private final ApiEntity<Branch> apiEntity;
    
    public ApiEntity_Branch(final ApiEntity<Branch> apiEntity){
        this.apiEntity = apiEntity;
    }
}
