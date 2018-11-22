package com.steaml.steamlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SteamlogApplication{
	public static void main(String[] args) {
		SpringApplication.run(SteamlogApplication.class, args);
	}
}
