package nl.han.devops;

import nl.han.devops.priem.Priemgetal;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface PriemRepository extends CrudRepository<Priemgetal, Long> {
    BigInteger findFirstByOrderByPriemgetalDesc();

    @Query("SELECT MAX(priemgetal) FROM PriemTest where isPriem = true AND datum_tijd > CURRENT_DATE")
    BigInteger hoogstePriemGetalVandaag();
}