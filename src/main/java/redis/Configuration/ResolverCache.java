package redis.Configuration;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component("dynamicResolverCache")
public class ResolverCache implements CacheResolver {


    private final CacheManager managerForUser ;
    private final CacheManager managerForTeacher ;
    private final CacheManager managerForAdmin ;

    private final ApplicationContext applicationContext ;

    public ResolverCache(

            @Qualifier("firstCacheManager")
            CacheManager managerForUser,

            @Qualifier("secondCacheManager")
            CacheManager managerForTeacher,


            @Qualifier("thirdCacheManager")
            CacheManager managerForAdmin,


            ApplicationContext applicationContext) {

        this.managerForUser = managerForUser;
        this.managerForTeacher = managerForTeacher;
        this.managerForAdmin = managerForAdmin;
        this.applicationContext = applicationContext;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {

        Set<String> cacheNames = context.getOperation().getCacheNames();

        CacheManager selectedCacheManager = null  ;
        Cache cache = null ;
        for (String cacheName : cacheNames) {
             if(cacheName.equals("users") || cacheName.equals("products")){

                 selectedCacheManager = managerForUser ;
                 cache = selectedCacheManager.getCache(cacheName) ;

             }else if ( cacheName.equals("teachers") || cacheName.equals("students")){

                 selectedCacheManager = managerForTeacher ;
                 cache = selectedCacheManager.getCache(cacheName) ;

             }else if ( cacheName.equals("admin") || cacheName.equals("employee")){

                 selectedCacheManager = managerForAdmin ;
                 cache = selectedCacheManager.getCache(cacheName) ;

             }

            assert selectedCacheManager != null;
            assert cache != null;

            System.out.println("Cache : " + cache.getName());



            String[] beanNames =
                    applicationContext.getBeanNamesForType(CacheManager.class);

            for (String beanName : beanNames) {

                CacheManager bean = applicationContext.getBean(beanName, CacheManager.class);

                if (bean == selectedCacheManager) {
                    System.out.println("Actually used bean: " + beanName);
                    break;
                }
            }
        }







       return List.of(cache) ;
    }
}
