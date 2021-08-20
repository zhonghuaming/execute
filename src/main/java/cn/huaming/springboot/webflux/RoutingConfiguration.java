package cn.huaming.springboot.webflux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 功能变体" WebFlux.fn"将路由配置与请求的实际处理分开
 */
@Configuration(proxyBeanMethods = false)
public class RoutingConfiguration {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return RouterFunctions
            .route(RequestPredicates.GET("/{user}").and(RequestPredicates.accept(APPLICATION_JSON)),
                userHandler::getUser)
            .andRoute(RequestPredicates.GET("/{user}/customers")
                .and(RequestPredicates.accept(APPLICATION_JSON)), userHandler::getUserCustomers)
            .andRoute(
                RequestPredicates.DELETE("/{user}").and(RequestPredicates.accept(APPLICATION_JSON)),
                userHandler::deleteUser);
    }

}

@Component
class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> getUserCustomers(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        return null;
    }
}