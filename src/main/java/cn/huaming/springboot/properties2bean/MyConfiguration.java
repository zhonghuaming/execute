package cn.huaming.springboot.properties2bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 全局方式:
 * @ConfigurationPropertiesScan
 * 但是：
 * 有时，用@ConfigurationProperties注释的类可能不适用于扫描，
 * 例如，如果您正在开发自己的自动配置，或者想要有条件地启用它们.
 * 在这些情况下，请使用@EnableConfigurationProperties批注指定要处理的类型列表.
 * 可以在任何@Configuration类上完成
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({AcmeProperties.class,BcmeProperties.class})
public class MyConfiguration {
}