package kov.develop.mvc.controller;

import org.junit.Test;

public class ControllerTest extends AbstractControllerTest {

    @Test
    public void userServiceTest() {
        System.out.println(userService.get(1));
        System.out.println(goodService.get(1));
        System.out.println(purchasingService.get(1));
    }
}
