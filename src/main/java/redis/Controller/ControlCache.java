package redis.Controller;


import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import redis.Service.ServiceCache;

@RestController
@RequestMapping("/api/cache")
public class ControlCache {

    private final ServiceCache serviceCache ;


    public ControlCache(ServiceCache serviceCache) {
        this.serviceCache = serviceCache;
    }

    @GetMapping("/users/{id}")
    public String saveUser(@PathVariable("id") int id )  {

        return serviceCache.getUsers(id);

    }

}
