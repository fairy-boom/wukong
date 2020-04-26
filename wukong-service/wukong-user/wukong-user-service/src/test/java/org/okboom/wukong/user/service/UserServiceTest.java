package org.okboom.wukong.user.service;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.okboom.wukong.user.UserApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tookbra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserServiceTest {

    @Reference(version = "${dubbo.provider.UserService.version}")
    UserService userService;

    @Test
    public void testAccountExists() {
        userService.exists("11");
    }
}
