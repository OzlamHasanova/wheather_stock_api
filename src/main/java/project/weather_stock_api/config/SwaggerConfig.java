package project.weather_stock_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        final Info apiInformation = getApiInformation();
        final Components components = new Components();

        final String schemeName = "bearerAuth";
        components.addSecuritySchemes(schemeName, new SecurityScheme().name(schemeName).type(HTTP).scheme("bearer").bearerFormat("JWT"));

        final OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(apiInformation);
        openAPI.setComponents(components);
        openAPI.addSecurityItem(new SecurityRequirement().addList(schemeName));

        return openAPI;
    }
    private Info getApiInformation() {
        final Info info = new Info();
        info.setTitle("Weather Stack Api");
        info.setVersion("1.0.0");
        info.setDescription("API documentation for the Weather Stack application");
       return info;
    }

}