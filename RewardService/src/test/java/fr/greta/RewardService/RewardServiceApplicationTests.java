package fr.greta.RewardService;

import fr.greta.RewardService.controller.RewardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RewardServiceApplicationTests {

	@Autowired
	RewardController rewardController;

	@Test
	void contextLoads() {
	}

}
