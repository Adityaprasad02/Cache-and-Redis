package redis.Controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import redis.Service.ServiceCache;

@RestController
@RequestMapping("/api/cache")
public class ControlCache {

    private final ServiceCache serviceCache ;


    public ControlCache( ServiceCache serviceCache) {
        this.serviceCache = serviceCache;
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") int id )  {

        return serviceCache.getUsers(id);

    }

    @GetMapping("/teachers/{tid}")
    public String getTeacher(@PathVariable("tid") int tid ){

        return serviceCache.getTeacher(tid) ;
    }

    @GetMapping("/common/{aid}")
    public String getAllByCache(@PathVariable("aid") int aid ){

        return serviceCache.getAllByCache(aid) ;
    }

    @PutMapping("/update/{kid}")
    public String update( @PathVariable("kid") int kid , @RequestParam String value ) {
        return serviceCache.updateEntity(kid , value ) ;
    }


    @GetMapping("/employee/{eid}")
    public String getEmployee(@PathVariable("eid") int aid ){

        return serviceCache.saveEmployee(aid) ;
    }

    @DeleteMapping("/delete/{id}")
    public String delete( @PathVariable("id") int id) {
       serviceCache.deleteEntity(id);
       return "deleted" +  id + " successfully " ;
    }

}
