package lsh.security.common.converter;

import org.springframework.core.convert.converter.Converter;

import lsh.security.constant.CityType;
import lsh.security.exception.CityTypeConverterException;

public class CityTypeConverter implements Converter<String, CityType>{

    @Override
    public CityType convert(String source) {
        for(CityType cityType: CityType.values()){
            if(cityType.toString().equalsIgnoreCase(source)){
                return cityType;
            }
        }

        throw new CityTypeConverterException(400,"CityType type cast error");
    }

}
