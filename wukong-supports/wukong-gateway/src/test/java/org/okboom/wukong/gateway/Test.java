package org.okboom.wukong.gateway;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author tookbra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {

    @Autowired
    WebTestClient webTestClient;

    @org.junit.Test
    public void testUser() {
        webTestClient.get().uri("/user/exists").exchange().expectStatus().isOk();
    }
}
