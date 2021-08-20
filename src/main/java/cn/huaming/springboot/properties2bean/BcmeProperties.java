package cn.huaming.springboot.properties2bean;

import cn.huaming.springboot.properties2bean.AcmeProperties.Security;
import java.net.InetAddress;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * 需要添加
 * @EnableConfigurationProperties
 * @ConfigurationPropertiesScan
 */
@Getter
@ConfigurationProperties("bcme")
@ConstructorBinding
public class BcmeProperties {

    private boolean enabled;

    private InetAddress remoteAddress;

    public BcmeProperties(boolean enabled, InetAddress remoteAddress, Security2 security) {
        this.enabled = enabled;
        this.remoteAddress = remoteAddress;
        this.security = security;
    }

    private Security2 security;

    @Getter
    public static class Security2 {

        private String username;

        private String password;

        private List<String> roles;

        public Security2(String username, String password,
            @DefaultValue("USER2") List<String> roles) {
            this.username = username;
            this.password = password;
            this.roles = roles;
        }

    }

}