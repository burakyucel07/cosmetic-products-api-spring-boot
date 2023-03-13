package net.burakyucel.cosmeticproductsapi.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
            Product cucumberWatermelon = new Product(
                    1L,
                    "Cucumber Watermelon",
                    "Shower Gel",
                    "A cool shower gel.",
                    10.50f,
                    250,
                    "Propylene glycol, water."
            );

            repository.saveAll(
                    List.of(cucumberWatermelon)
            );
        };
    }
}
