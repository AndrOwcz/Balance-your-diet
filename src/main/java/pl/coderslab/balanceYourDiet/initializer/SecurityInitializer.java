package pl.coderslab.balanceYourDiet.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import pl.coderslab.balanceYourDiet.configuration.SecurityConfiguration;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(SecurityConfiguration.class);
    }
}
