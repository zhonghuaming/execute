package cn.huaming.fastjson;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 注解@ScProperty处理器
 *
 * @author Jorvey
 * @date 2020年7月16日
 */
@Aspect
@Component
@Order(0)
@Slf4j
public class ScPropertyAspectHandler {

    @Around(value = "@annotation(scProperty)")
    public Object around(ProceedingJoinPoint joinPoint, ScProperty scProperty) throws Throwable {

        if (scProperty != null) {

            setExcludes(scProperty.excludes());

            seIncludes(scProperty.includes(), scProperty.isResult());

        }
        return joinPoint.proceed();
    }

    /**
     * 将includes设置到ThreadLocal
     *
     * @param includesStr
     * @param isResult
     */
    private void seIncludes(String includesStr, boolean isResult) {
        if (StringUtils.hasText(includesStr)) {
            if (isResult) {
                String defaults = "errcode,errmsg,data,";
                includesStr = defaults + includesStr;
            }
            ScPropertyContext.setIncludes(Stream.of(includesStr.split(",")).collect(Collectors.toSet()));
        }
    }

    /**
     * 将excludes设置到ThreadLocal
     *
     * @param excludesStr
     */
    private void setExcludes(String excludesStr) {
        if (StringUtils.hasText(excludesStr)) {
            ScPropertyContext.setExcludes(Stream.of(excludesStr.split(",")).collect(Collectors.toSet()));
        }
    }
}
