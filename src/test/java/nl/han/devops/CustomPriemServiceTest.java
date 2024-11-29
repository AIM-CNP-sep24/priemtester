package nl.han.devops;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.stream.Stream;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomPriemServiceTest {

	private final PriemService priemService = new CustomPriemService();

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 1})
	void isPriemgetal_waar_voor_getallen_kleiner_dan_twee(int value) {
		// Arrange.
		var expected = new PriemResponse(
				Integer.toString(value),
				false,
				new BigInteger("0"),
				value + " is geen priemgetal, want het is kleiner dan 2."
		);
		// Act
		var result = priemService.isPriemgetal(value);

		Assertions.assertEquals(expected, result);
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20})
	public void isPriemgetal_onwaar_voor_niet_priemgetallen(int value) {
		boolean result = priemService.isPriemgetal(value);

		Assertions.assertFalse(result, value + " zou geen priemgetal moeten zijn.");
	}
	@ParameterizedTest
	@ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19})
	public void isPriemgetal_waar_voor_priemgetallen(int value) {
		boolean result = priemService.isPriemgetal(value);

		Assertions.assertTrue(result, value + " zou een priemgetal moeten zijn.");
	}

	// Method source to provide BigInteger values
	private static Stream<BigInteger> provideBigIntegerPrimes() {
		return Stream.of(
				new BigInteger("2"),
				new BigInteger("3"),
				new BigInteger("5"),
				new BigInteger("7"),
				new BigInteger("11"),
				new BigInteger("13"),
				new BigInteger("17"),
				new BigInteger("19"),
				new BigInteger("32416190071") // Large prime number for testing
		);
	}

	@ParameterizedTest
	@MethodSource("provideBigIntegerPrimes")
	public void isPriemgetal_shouldReturnTrue_forPrimeNumbers(BigInteger value) {
		boolean result = priemService.isPriemgetal(value);

		Assertions.assertTrue(result, value + " should be a prime number.");
	}

}