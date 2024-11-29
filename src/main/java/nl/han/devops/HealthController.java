package nl.han.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
// @Tag(name = "PriemController", description = "Endpoints for prime number checking")
public class HealthController {

    // Custom end point
    // In principe inmiddels vervangen via standaard actuator/healt end point
    // Van Sprint Boot actuator. Maar voor het concept van de opdracht is dit
    // een custom end point.
    @GetMapping
    public String isHealthy() {
            return "healthy";
    }

}
