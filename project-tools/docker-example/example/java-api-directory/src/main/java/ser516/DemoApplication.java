package ser516;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // A simple endpoint that the Flask app can call
    @GetMapping("/api/data")
    public String getData() {
        return "{\"message\": \"Hello from the Java REST API!\"}";
    }
}