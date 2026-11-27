package redis.Service;


import org.apache.catalina.core.ApplicationContext;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    public ServiceCache(@Qualifier("firstCacheManager") CacheManager cacheManager) {
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


     // The Annotation in controller need not to be @PutMapping because both are independent
    @CachePut(
            cacheNames = "users" ,
            key = "#kid" ,
            cacheResolver = "dynamicResolverCache"
    )
    public String updateEntity( int kid , String name ){
           return "user-" + kid + "_" + name ;
    }



    @Cacheable(
            cacheNames = "employee" ,
            key = "#eid" ,
            cacheResolver = "dynamicResolverCache"
    )
    public String saveEmployee( int eid )  {
        return "empID: " + eid  ;
    }


    @CacheEvict(
            cacheNames = "employee" ,
            allEntries = true , // false by default , so only key = "#id" gets deleted
            cacheResolver = "dynamicResolverCache" ,
            beforeInvocation = true //by default its true , delete before method execute then perform other oprns
    )
    public void deleteEntity(int id) {

        // interact with repository to delete

        System.out.println("entity-" + id + "deleted successfully");
    }
}
