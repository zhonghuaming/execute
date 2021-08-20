package cn.huaming.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerialContext;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 定制的属性预处理过滤器
 *
 * @author Jorvey
 * @date 2020年7月16日
 */
public class ScPropertyPreFilter implements PropertyPreFilter {

    public int getMaxLevel() {
        return 0;
    }

    public Class<?> getClazz() {
        return ScPropertyContext.getClazz();
    }

    public Set<String> getIncludes() {
        return ScPropertyContext.getIncludes();
    }

    public Set<String> getExcludes() {
        return ScPropertyContext.getExcludes();
    }

    /**
     * 先处理excludes,然后处理includes
     */
    @Override
    public boolean apply(JSONSerializer serializer, Object source, String name) {

        if (source == null) {
            return true;
        }

        if (this.getClazz() != null && !this.getClazz().isInstance(source)) {
            return true;
        }

        Set<String> includes = Optional.ofNullable(getIncludes()).orElse(new HashSet<>());
        Set<String> excludes = Optional.ofNullable(getExcludes()).orElse(new HashSet<>());

        if (excludes.contains(name)) {
            return false;
        }

        //不知道为什么会有这几行代码， 但是为了和FastJson原来的行为一致，还是保留下来
        if (getMaxLevel() > 0) {
            int level = 0;
            SerialContext context = serializer.getContext();
            while (context != null) {
                level++;
                if (level > getMaxLevel()) {
                    return false;
                }
                context = context.parent;
            }
        }

        if (includes.size() == 0 || includes.contains(name)) {
            return true;
        }

        return false;
    }

}
