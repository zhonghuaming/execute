package cn.huaming.fastjson;

import java.util.Set;

/**
 * 用于获取同一个线程的属性
 *
 * @author Jorvey
 * @date 2020年7月16日
 */
public class ScPropertyContext {

    private static final ThreadLocal<Set<String>> INCLUDES_CONTEXT_HOLDER = new ThreadLocal<>();
    private static final ThreadLocal<Set<String>> EXCLUDES_CONTEXT_HOLDER = new ThreadLocal<>();
    private static final ThreadLocal<Class<?>> CLASS_CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setIncludes(Set<String> includes) {
        INCLUDES_CONTEXT_HOLDER.set(includes);
    }

    public static Set<String> getIncludes() {
        return INCLUDES_CONTEXT_HOLDER.get();
    }

    public static void clearInclude() {
        INCLUDES_CONTEXT_HOLDER.remove();
    }

    public static void setExcludes(Set<String> excludes) {
        EXCLUDES_CONTEXT_HOLDER.set(excludes);
    }

    public static Set<String> getExcludes() {
        return EXCLUDES_CONTEXT_HOLDER.get();
    }

    public static void clearExcludes() {
        EXCLUDES_CONTEXT_HOLDER.remove();
    }

    public static void setClazz(Class<?> clazz) {
        CLASS_CONTEXT_HOLDER.set(clazz);
    }

    public static Class<?> getClazz() {
        return CLASS_CONTEXT_HOLDER.get();
    }

    public static void clearClazz() {
        CLASS_CONTEXT_HOLDER.remove();
    }
}
