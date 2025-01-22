package com.example.WorldAPI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class WorldApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
