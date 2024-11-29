package nl.han.devops;

import nl.han.devops.priem.IsPriemRequest;
import nl.han.devops.priem.Priemgetal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/priem")
// @Tag(name = "PriemController", description = "Endpoints for prime number checking")
public class PriemController {

    private final PriemService priemService;

    private final PriemRepository priemRepository;

    @Autowired
    public PriemController(PriemService priemService, PriemRepository priemRepository) {
        this.priemService = priemService;
        this.priemRepository = priemRepository;
    }

    @GetMapping("/getAll")
    public List<Priemgetal> getAllPriemgetallen() {
        return (List<Priemgetal>) priemRepository.findAll();
    }

    @PostMapping
    public PriemResponse checkOfPriem(@RequestBody IsPriemRequest input) {
        String numberStr = input.getNumber();
        try {
            // First, try to parse as Integer
            Integer intValue = Integer.valueOf(numberStr);
            var result = new PriemResponse(numberStr, priemService.isPriemgetal(intValue), priemService.getHoogstePriemTotNu(), "OK");
            return ;
        } catch (NumberFormatException ex) {
            // If it fails, parse as BigInteger
            // TODO: Alleen toestaan voor ingelogde gebruikers.
            try {
                BigInteger bigIntValue = new BigInteger(numberStr);
                return priemService.isPriemgetal(bigIntValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input: Not a valid number");
            }
        }
    }
}
