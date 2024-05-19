package kusitms.duduk.apiserver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Duduk Server API",
        version = "1.0.0",
        description = "Duduk Server API 명세서입니다.",
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
    ),
    servers = {
        @Server(url = "http://localhost:8080/", description = "Local Server"),
        @Server(url = "https://goodmoneying.shop/", description = "Production Server")
    }, security = @SecurityRequirement(name = "Bearer Token")
)
@SecuritySchemes({
    @SecurityScheme(
        name = "Bearer Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "JWT Bearer Token"
    )
})
@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .components(new Components());
    }
//    @Bean
//    public OpenAPI openApi() {
//        return new OpenAPI()
//            .components(new Components()
//                .addSecuritySchemes(SECURITY_SCHEME_NAME, new io.swagger.v3.oas.models.security.SecurityScheme()
//                    .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
//                    .name(SECURITY_SCHEME_NAME)
//                    .scheme("Bearer")
//                    .bearerFormat("JWT")))
//            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
//    }
}
