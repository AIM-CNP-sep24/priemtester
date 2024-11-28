package nl.han.devops;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import org.slf4j.*;
import java.math.BigInteger;


// Make this a console AND a web application

@SpringBootApplication
public class PriemTesterApplication implements CommandLineRunner {

	
	@Autowired
	private Environment environment;

	private static final Logger logger = LoggerFactory.getLogger(PriemTesterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PriemTesterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var customPriemTester = new CustomPriemService();
		logger.info("Custom priemtester");
		for(int i = 0; i<10; i++) {
			logger.info("Is {} een priemgetal? {}", i, customPriemTester.isPriemgetal(i));
		}

		var mavenPriemTester = new MavenPriemService();

		logger.info("");
		logger.info("Maven based priemtester");

		// BigInteger start = BigInteger.ZERO; // Start at 0
		final BigInteger start = BigInteger.valueOf(32452842);
		BigInteger increment = BigInteger.ONE; // Increment by 1

		// End is 10 further.
		BigInteger diff = BigInteger.TEN;
		BigInteger end = start.add(diff);

		for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(increment)) {
			var isPriem = mavenPriemTester.isPriemgetal(i);
			
			logger.info("Is {} een priemgetal? {}", i, isPriem);
		}

		String port = environment.getProperty("server.port", "8080"); // Default to 8080 if not set
		logger.info("Web application started on http://localhost:{}/", port);
	}
}
