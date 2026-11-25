package redis.Service;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiceCache {
    private final CacheManager cacheManager ;

    public ServiceCache(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Cacheable( cacheNames = "users", key = "#id" )
    public String getUsers (int id)  {
        System.out.println(" running getUsers() ");

        // CacheManager details
        System.out.println("CacheManager Class: "
                + cacheManager.getClass().getName());

        // Cache details
        Cache cache = cacheManager.getCache("users");

        System.out.println("Cache Name: "
                + Objects.requireNonNull(cache).getName());

        System.out.println("Cache Implementation: "
                + cache.getClass().getName());
        return "User-" + id ;
    }
}
