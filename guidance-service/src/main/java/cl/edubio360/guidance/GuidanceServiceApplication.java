package cl.edubio360.guidance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GuidanceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuidanceServiceApplication.class, args);
    }
}
