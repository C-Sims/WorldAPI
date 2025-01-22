package com.example.WorldAPI;

import org.springframework.boot.SpringApplication;

public class TestWorldApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(WorldApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
