package lsh.security.constant.nested;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonCreator;

import lsh.security.exception.CityTypeConverterException;

public enum CityType {
    SEOUL,
    BUSAN,
    DAEGU;

    @JsonCreator
    public static CityType from(String source) {
        for(CityType cityType: values()){
            if(cityType.toString().equalsIgnoreCase(source)){
                return cityType;
            }
        }

        throw new CityTypeConverterException(HttpStatus.BAD_REQUEST,"CityType type cast error");
    }

}
