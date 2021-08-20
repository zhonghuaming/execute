package cn.huaming;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class ExerciseApplication {

  public static void main(String[] args) {
    // 再启动之前添加属性不会触发重新加载，等同于在application-dev.properties添加spring.devtools.restart.enabled=false
    //        System.setProperty("spring.devtools.restart.enabled", "false");
    //        SpringApplication.run(ExerciseApplication.class, args);

    //        System.setProperty("server.port","8084");//优先级比properties高
    SpringApplication springApplication = new SpringApplication(ExerciseApplication.class);
    HashMap<String, Object> map = new HashMap<>();
    //        map.put("server.port","8083");//优先级比properties低
    springApplication.setDefaultProperties(map);
    springApplication.setBannerMode(Banner.Mode.OFF);
    String[] strings = {"debug"};
    springApplication.run(strings);
    //        new SpringApplicationBuilder()
    //            .sources(ExerciseApplication.class)
    //            .child(ExerciseApplication.class)
    //            .bannerMode(Banner.Mode.OFF)
    //            .run(args);

  }

  //    @Bean
  //    public FilterRegistrationBean setFilter() {
  //        FilterRegistrationBean filterBean = new FilterRegistrationBean();
  //		filterBean.setFilter(new OpenSessionInViewFilter());
  //        filterBean.setFilter(new OpenEntityManagerInViewFilter());
  //        filterBean.setName("FilterController");
  //        filterBean.addUrlPatterns("/*");
  //        return filterBean;
  //    }
}
