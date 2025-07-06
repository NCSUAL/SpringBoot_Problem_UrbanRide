package lsh.security.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lsh.security.domain.Branch;
import lsh.security.domain.Vehicle;

public class VehicleSpecification {

    public static Specification<Vehicle> associationBranch(){
        return (root, query, criteriaBuilder) -> {
            root.fetch("branch", JoinType.INNER);

            Join<Vehicle, Branch> join = root.join("branch",JoinType.INNER); 

            return criteriaBuilder.isNotNull(join.get("id"));
        };
    }
}
