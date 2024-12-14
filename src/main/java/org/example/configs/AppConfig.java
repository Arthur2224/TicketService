package org.example.configs;

import org.example.exceptions.CustomLoginErrorHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up application-specific beans.
 * This class includes beans for handling custom login error behavior
 */
@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    /**
     * Bean for configuring a custom login error handler.
     * This bean is only created if the property 'block.login.enabled' is set to 'true' in the application properties.
     *
     * @return an instance of {@link CustomLoginErrorHandler} with login blocking enabled
     */
    @Bean
    @ConditionalOnProperty(name = "block.login.enabled", havingValue = "true")
    public CustomLoginErrorHandler customErrorHandler() {
        CustomLoginErrorHandler errorHandler = new CustomLoginErrorHandler();
        errorHandler.setBlockLogin(true);
        return errorHandler;
    }

}
