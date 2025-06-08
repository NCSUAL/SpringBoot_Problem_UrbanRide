package lsh.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lsh.security.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{
    
}
