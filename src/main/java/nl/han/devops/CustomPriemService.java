package nl.han.devops;

import nl.han.devops.priem.IsPriemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;

@Service
public class CustomPriemService implements PriemService {

    @Autowired
    TijdService tijdService = new TijdService();

    BigInteger hoogstePriemVandaag;

    @Autowired
    private PriemRepository priemRepository;

    private LocalDate vandaag;

    CustomPriemService(PriemRepository priemRepository) {
        this.priemRepository = priemRepository;
        hoogstePriemVandaag = priemRepository.hoogstePriemGetalVandaag();
        vandaag = LocalDate.now();
    }

    @Override
    public IsPriemResponse isPriemgetal(final int kandidaat) {
        // Als het getal kleiner is dan 2, is het geen priemgetal
        boolean isPriemgetal;
        if (kandidaat < 2) {
            return new IsPriemResponse(Integer.toString(kandidaat), false, hoogstePriemVandaag, kandidaat + " is geen priemgetal, want het is kleiner dan 2.");
        }

        // Controleer divisors tot en met de vierkantswortel van de kandidaat
        for (int deler = 2; deler <= Math.sqrt(kandidaat); deler++) {
            if (kandidaat % deler == 0) {
                return new IsPriemResponse(Integer.toString(kandidaat), false, hoogstePriemVandaag, kandidaat + " is geen priemgetal, want het kan worden gedeeld door " + deler + ".");// Als er een deler is, is het geen priemgetal
            }
        }
        var hetIsNieuweDag = LocalDate.now().isAfter(vandaag);
        if (kandidaat > hoogstePriemVandaag || tijdService.isNieuweDag(vandaag)) {
            vandaag = LocalDate.now();
            hoogstePriemVandaag = kandidaat;
        }
        // Als geen delers zijn gevonden, is het getal een priemgetal
        return new IsPriemResponse(Integer.toString(kandidaat), true, hoogstePriemVandaag, kandidaat + " is een priemgetal.");
    }

    @Override
    public IsPriemResponse isPriemgetal(BigInteger kandidaat) {
        // Gebruik standaard BigInteger ProbablePrime
        final int CERTAINTY = 15;
        var result = kandidaat.isProbablePrime(CERTAINTY);

        if (!result) {
            return new PriemResponse(kandidaat.toString(), false, hoogstePriemVandaag, kandidaat + " is geen priemgetal.");
        }

        // Bron: https://chatgpt.com/share/672385e0-3ecc-8012-904f-75b95fbf69f3
        // Voor een deterministische test, probeer te delen door alle priemgetallen kleiner dan √kandidaat
        BigInteger sqrt = kandidaat.sqrt();
        BigInteger i = BigInteger.valueOf(2);

        while (i.compareTo(sqrt) <= 0) {
            if (kandidaat.mod(i).equals(BigInteger.ZERO)) {
                return false;  // Kan gedeeld worden, dus niet priem
            }
            i = i.nextProbablePrime();  // Spring naar het volgende priemgetal
        }

        // Geen factoren gevonden, dus het is een priemgetal
        return true;
    }
}