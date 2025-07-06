package lsh.security.common.convert;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import lsh.security.constant.nested.Role;
import lsh.security.exception.NotConvertException;

@Component
public class RoleConverter implements AttributeConverter<Role, String>{

    @Override
    public String convertToDatabaseColumn(Role attribute) {
        if(Objects.isNull(attribute)){
            throw new NotConvertException(HttpStatus.BAD_REQUEST, "role 값이 빈 값입니다.");
        }
        else{
            return attribute.getRole();
        }
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        for(Role role: Role.values()){
                if(role.getRole().equalsIgnoreCase(dbData)){
                    return role;
                }
         }

         throw new NotConvertException(HttpStatus.BAD_REQUEST, "role 값이 올바르지 않습니다.");

    }


}
