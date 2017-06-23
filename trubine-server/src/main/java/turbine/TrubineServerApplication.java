package turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TrubineServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrubineServerApplication.class, args);
	}
}
