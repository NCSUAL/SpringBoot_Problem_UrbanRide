package lsh.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lsh.security.constant.ServerList;
import lsh.security.utils.InfoUtil;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
        .components(new Components())
        .servers(server())
        .info(info());
    }

    @Bean
    public List<Server> server(){
        return ServerList.toList();
    }

    @Bean
    public Info info(){
        return new Info()
            .title("스프링 문제")
            .version("x.xx")
            .description(InfoUtil.problemDescription().get())
            .summary("summary");
    }
}