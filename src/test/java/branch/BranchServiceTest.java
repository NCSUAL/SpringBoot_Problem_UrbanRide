package branch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lsh.security.repository.BranchRepository;
import lsh.security.service.branch.BranchService;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {
    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Test
    public void CRUD_CREATE_기능_테스트(){
        
    }
}
