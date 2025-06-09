package lsh.security.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lsh.security.common.resolver.EnumConverterResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // add configuration EnumConverterResolver
        resolvers.add(new EnumConverterResolver());
    }   

}
