package com.gbm.challenge.gbmchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {
//		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		HibernateJpaAutoConfiguration.class})
public class GbmChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GbmChallengeApplication.class, args);
	}

}
