package com.dbdbdip.giftmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GiftmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftmanagementApplication.class, args);
	}

}
