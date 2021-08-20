package cn.huaming.springboot.servlet;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * 如果您需要以编程方式配置嵌入式servlet容器，则可以注册一个实现WebServerFactoryCustomizer接口的Spring Bean.
 * WebServerFactoryCustomizer提供对ConfigurableServletWebServerFactory访问，其中包括许多自定义设置方法.
 * 以下示例显示以编程方式设置端口
 * @author Jorvey
 */
@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
//        server.setPort(9000);
    }

}