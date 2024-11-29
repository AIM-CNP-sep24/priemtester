package nl.han.devops;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@Tag(name = "PriemController", description = "Endpoints for (custom) health check")
public class HealthController {

    @GetMapping
    public String isHealthy() {
            return "healthy";
    }

}
