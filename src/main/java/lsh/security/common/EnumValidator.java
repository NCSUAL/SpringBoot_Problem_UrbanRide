package lsh.security.common;

import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lsh.security.common.annotaion.EnumValid;
import lsh.security.constant.nested.EnumException;
import lsh.security.exception.EnumConversionException;
import lsh.security.exception.InvalidEnumValueException;

public class EnumValidator implements ConstraintValidator<EnumValid,Object>{

    private EnumValid enumValid;

    @Override
    public void initialize(EnumValid constraintAnnotation) {

        enumValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(Objects.isNull(enumValid)){
            throw new InvalidEnumValueException(EnumException.INVALID_ENUM.toString());
        }

        try{
            if(value instanceof String arg){
                Enum.valueOf(enumValid.value().asSubclass(Enum.class), arg);

                return true;
            }
        }
        catch(Exception exception){
            throw new EnumConversionException(EnumException.CONVERSION_ENUM.toString());
        }
        throw new EnumConversionException(EnumException.CONVERSION_ENUM.toString());
    }


}
