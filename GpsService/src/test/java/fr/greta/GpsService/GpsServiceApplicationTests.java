package fr.greta.GpsService;

import fr.greta.GpsService.controller.GpsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GpsServiceApplicationTests {
	@Autowired
	private GpsController gpsController;

	@Test
	void contextLoads() {
		assertNotNull(gpsController);
	}

}
