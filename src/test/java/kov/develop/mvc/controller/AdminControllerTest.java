package kov.develop.mvc.controller;

import net.sf.ehcache.CacheManager;
import org.junit.Test;

public class AdminControllerTest extends AbstractControllerTest {

    @Test
    public void checkCache() {
        CacheManager.ALL_CACHE_MANAGERS.forEach(a -> System.out.println(a.getCacheNames()));
    }
}
