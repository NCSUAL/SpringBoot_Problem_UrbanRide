package lsh.security.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lsh.security.common.annotaion.EnumConverter;

public class EnumConverterResolver implements HandlerMethodArgumentResolver{

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameter().isAnnotationPresent(EnumConverter.class);
    }

    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        System.out.println("동작함!");
        String parameterValue = webRequest.getParameter(parameter.getParameterName());
        Class<?> classInstance = parameter.getParameter().getType();

        System.out.println(parameterValue);
        System.out.println(classInstance);

        if(Enum.class.isAssignableFrom(classInstance)){
            try{
                return Enum.valueOf((Class<Enum>)classInstance, parameterValue);
            }
            catch(Exception exception){
                throw new UnsupportedOperationException("Unimplemented method 'resolveArgument'");
            }
        }

        throw new UnsupportedOperationException("Unimplemented method 'resolveArgument'");
    }

}
