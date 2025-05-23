package ch.jano.dreier.OnlineShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OnlineShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}
}
