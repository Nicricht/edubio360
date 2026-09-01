package cl.edubio360.auth;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoUsersConfig {
    @Bean
    ApplicationRunner loadDemoUsers(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            createIfMissing(users, encoder, "student@edubio.local", "Student123!", "STUDENT");
            createIfMissing(users, encoder, "orientador@edubio.local", "Orientador123!", "ORIENTADOR");
            createIfMissing(users, encoder, "admin@edubio.local", "Admin123!", "ADMIN");
        };
    }

    private void createIfMissing(UserRepository users, PasswordEncoder encoder,
                                 String email, String password, String role) {
        if (!users.existsByEmailIgnoreCase(email)) {
            users.save(new UserEntity(email, encoder.encode(password), role));
        }
    }
}
