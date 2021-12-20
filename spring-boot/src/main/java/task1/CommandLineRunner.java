package task1;

import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello, world!");
    }
}
