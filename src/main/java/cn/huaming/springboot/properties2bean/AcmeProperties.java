package cn.huaming.springboot.properties2bean;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 需要添加
 * @EnableConfigurationProperties
 * @ConfigurationPropertiesScan
 */
@Setter
@Getter
@ConfigurationProperties("acme")
public class AcmeProperties {

    private boolean enabled;

    private InetAddress remoteAddress;

    private Security security = new Security();

    private Map map;

    @Getter@Setter
    public static class Security {

        private String username;

        private String password;

        private List<String> roles = new ArrayList<>(Collections.singleton("USER"));

    }
}