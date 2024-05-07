package com.fdmgroup.gwbPlatformBulletin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.gwbPlatformBulletin.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class GwbPlatformBulletinApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwbPlatformBulletinApplication.class, args);
	}

}
