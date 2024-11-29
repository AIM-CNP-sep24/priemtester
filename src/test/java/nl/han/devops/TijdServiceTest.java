package nl.han.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TijdServiceTest {

    TijdService sut;

    @BeforeEach
    void setUp() {
        sut = new TijdService();
    }

    @Test
    void getVandaag() {
        sut.getVandaag();
    }

    @Test
    void isNieuweDagTest() {

    }
}