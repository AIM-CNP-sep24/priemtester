package nl.han.devops;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TijdService {

    public LocalDate getVandaag() {
        return LocalDate.now();
    }

    public boolean isNieuweDag(LocalDate datum) {
        return datum.isAfter(getVandaag());
    }
}
