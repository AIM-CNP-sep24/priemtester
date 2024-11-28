package nl.han.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
// @Tag(name = "PriemController", description = "Endpoints for prime number checking")
public class HealthController {

    @GetMapping
    public String isHealthy() {
            return "healthy";
    }

}
