package lsh.security.domain.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lsh.security.constant.nested.Role;
import lsh.security.domain.vo.AuditingTime;

@Entity
@Table(name = "user", schema ="user")
@SequenceGenerator(name = "USER_ID_GEN", sequenceName = "USER_ID", allocationSize = 25)
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User extends AuditingTime{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GEN")
    @Column(name = "USER_ID", unique = true)
    private Long id;  

    private String name;

    private String password;
    
    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private Role role;

}
