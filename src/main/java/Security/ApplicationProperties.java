
package Security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
@Getter
@Setter
@Data
@ToString
public class ApplicationProperties {
       @Value("${app.baseUrl}")
       private String appBaseUrl;
      
}