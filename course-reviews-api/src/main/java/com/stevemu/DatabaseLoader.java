package com.stevemu;

import com.stevemu.user.User;
import com.stevemu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public DatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User p1 = User.builder()
                .username("stevemu")
                .firstName("Qi")
                .lastName("Mu")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_ADMIN"))
                .build();

        userRepository.save(p1);
    }
}
