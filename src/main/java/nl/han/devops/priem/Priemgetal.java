package nl.han.devops.priem;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;

@Table("Priemgetallen")
public class Priemgetal {
    @Id
    private Long id;
    private BigInteger kandidaat;
    private boolean isPriem;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getKandidaat() {
        return kandidaat;
    }

    public void setKandidaat(BigInteger kandidaat) {
        this.kandidaat = kandidaat;
    }

    public boolean isPriem() {
        return isPriem;
    }

    public void setPriem(boolean isPriem) {
        this.isPriem = isPriem;
    }
}