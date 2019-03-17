package daniella.iths.se.librarystorageservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class Config {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
