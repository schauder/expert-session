package com.example.thorbenexpertsession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ThorbenExpertSessionApplicationTests {

	@Autowired
	Minions minions;

	@Autowired
	Persons persons;

	private Person gru;
	private Person scarlet;
	private Minion kevin;

	@BeforeEach
	void before(){

		gru = persons.save(new Person("Gru"));
		scarlet = persons.save(new Person("Scarlet"));

		Minion minion = new Minion("Kevin");
		minion.add("Blue Light");
		minion.add("Trumpet");
		minion.add("Axe");

		minion.setMaster(gru);

		kevin = minions.save(minion);

	}

	@Test
	void playWithMinion() {


		Minion reloaded = minions.findById(kevin.id).orElseThrow();

		System.out.println(reloaded);

		assertThat(kevin).isEqualTo(reloaded);
		assertThat(kevin).isNotSameAs(reloaded);
		assertThat(kevin.master.getId()).isEqualTo(gru.id());

		minions.deleteById(kevin.id);

		assertThat(minions.findAll()).isEmpty();

	}
}
