package com.lahiru.ims.config;

import com.lahiru.ims.features.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupCommandLineRunner implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.saveUser("admin", "1234");
    }
}
