package nl.han.devops;

import nl.han.devops.priem.IsPriemRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// Bron: https://spring.io/guides/gs/testing-web
// Spring Boot test? Je moet toch alleen je eigen code testen, niet de frameworks of libraries die je gebruikt.
@SpringBootTest
@AutoConfigureMockMvc
class PriemControllerTest {

    @Autowired
    private PriemController sut;

    @MockBean
    private PriemService mockPriemService;

    @BeforeEach
    void setUp() {
        sut = new PriemController(mockPriemService);
    }

    @Test
    void PriemControllerBigIntegerReturnsWhatServiceReturns() {
        // Arrange.
        var input = Instancio.create(BigInteger.class);
        var expected = Instancio.create(Boolean.class);
        when(mockPriemService.isPriemgetal(input)).thenReturn(expected);

        // Act
        var actual = sut.checkOfPriem(new IsPriemRequest(input.toString()));

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}