package kov.develop.mvc.controller;

import net.sf.ehcache.CacheManager;
import org.junit.Test;

public class AdminControllerTest extends AbstractControllerTest {

    @Test
    public void checkCache() {
        goodService.findAll();
        goodService.findAll();
        goodService.findAll();
        int i = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("kov.develop.mvc.model.Good").getSize();
        System.out.println("*** - " + i);
    }
}
