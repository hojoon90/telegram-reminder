package com.bot.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramReminderApplication.class, args);
	}

}
