package redis.Configuration;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class AppConfig {


    @Bean
    @Primary
    public CacheManager firstCacheManager () {

        return new ConcurrentMapCacheManager(
                "users" , "products"
        );
    }

    @Bean
    public CacheManager secondCacheManager () {

        return new ConcurrentMapCacheManager(
                "teachers" , "students"
        ) ;
    }

    @Bean
    public CacheManager thirdCacheManager () {

        return new ConcurrentMapCacheManager(
                "admin" , "employee"
        ) ;
    }










}
