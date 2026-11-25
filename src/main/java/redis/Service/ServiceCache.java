package redis.Service;


import org.apache.catalina.core.ApplicationContext;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ServiceCache {
    private final CacheManager cacheManager ;

    public ServiceCache(@Qualifier("secondCacheManager") CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Cacheable( cacheNames = "users", key = "#id")
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

    @Cacheable( cacheNames = "teachers" , key = "#tid" , cacheManager = "secondCacheManager")
    public String getTeacher(int tid) {

        // Cache details
        Cache cache = cacheManager.getCache("teachers");

        System.out.println("Cache Name: "
                + Objects.requireNonNull(cache).getName());

        System.out.println("Cache Implementation: "
                + cache.getClass().getName());

        System.out.println("cacheManager simple name ; " + cacheManager.getClass().getSimpleName());

        return "Teachers-" + tid ;
    }


    // for dynamically provide cache-Manager on run time
    @Cacheable( cacheNames = {"admin","products" , "students"} , key = "#aid" , cacheResolver = "dynamicResolverCache")
    public String getAllByCache( int aid ) {
        return "ALL-" + aid ;
     }
}
