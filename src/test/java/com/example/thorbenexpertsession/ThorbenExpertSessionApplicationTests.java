package com.example.thorbenexpertsession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ThorbenExpertSessionApplicationTests {

	@Autowired
	Minions minions;

	@Test
	void playWithMinion() {

		Minion minion = new Minion();
		minion.name = "Kevin";

		Minion saved = minions.save(minion);

		assertThat(saved.id).isNotNull();

		assertThat(saved).isSameAs(minion);

		Minion reloaded = minions.findById(saved.id).orElseThrow();

		assertThat(saved).isEqualTo(reloaded);
		assertThat(saved).isNotSameAs(reloaded);

		minions.deleteById(saved.id);

		assertThat(minions.findAll()).isEmpty();

	}

}
