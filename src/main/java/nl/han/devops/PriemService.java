package nl.han.devops;

import nl.han.devops.priem.IsPriemResponse;

import java.math.BigInteger;

public interface PriemService {
    public IsPriemResponse isPriemgetal(int kandidaat);
    public IsPriemResponse isPriemgetal(BigInteger kandidaat);
}
