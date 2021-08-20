package cn.huaming.springboot.webflux;

import cn.huaming.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring WebFlux是Spring Framework 5.0中引入的新的响应式Web框架
 * 基于注释的模型非常类似于Spring MVC模型
 */
@RestController
@RequestMapping("/users")
public class MyRestController {

    @GetMapping("/{user}")
    public Mono<User> getUser(@PathVariable Long user) {
        return null;
    }

    @GetMapping("/{user}/customers")
    public Flux<User> getUserCustomers(@PathVariable Long user) {
        return null;
    }

    @DeleteMapping("/{user}")
    public Mono<User> deleteUser(@PathVariable Long user) {
        return null;
    }

}