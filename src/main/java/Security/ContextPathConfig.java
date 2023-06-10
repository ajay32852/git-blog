
package Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextPathConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public String getContextPath() {
        return contextPath;
    }
}