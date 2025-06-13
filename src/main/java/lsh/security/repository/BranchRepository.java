package lsh.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lsh.security.domain.Branch;


public interface BranchRepository extends JpaRepository<Branch, Long>{
    
    Optional<Branch> findByName(String name);
}
