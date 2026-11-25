package redis;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;

@SpringBootApplication
public class RedisApplication   {


    public static void main(String[] args)   {
		SpringApplication.run(RedisApplication.class, args);
	}

}
