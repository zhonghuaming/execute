package cn.huaming.fastjson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性过滤注解
 * 用逗号隔开
 *
 * example:
 *
 *- @PostMapping("/login")
 *- @ScProperty(excludes = "nickname,username")
 *- public Result login(@RequestBody SysUsers bean){...}
 *
 * @author Jorvey
 * @date 2020年07月16日
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ScProperty {

    String includes() default "";

    String excludes() default "";

    /**
     * 默认包含 ["errcode,errmsg,data,"]
     *
     * @return
     */
    boolean isResult() default true;


}
