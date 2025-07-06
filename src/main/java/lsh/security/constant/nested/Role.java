package lsh.security.constant.nested;

import lombok.Getter;

@Getter
public enum Role {

    USER("user"),
    ADMIN("admin");

    private String role;

    Role(final String role){
        this.role = role;
    }
}
