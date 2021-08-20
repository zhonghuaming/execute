package cn.huaming.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class JsonConfig extends WebMvcConfigurationSupport {


    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializeFilters(new ScPropertyPreFilter());
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.ALL);
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        fastMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        fastMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        fastMediaTypes.add(MediaType.APPLICATION_PDF);
        fastMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        fastMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        fastMediaTypes.add(MediaType.APPLICATION_XML);
        fastMediaTypes.add(MediaType.IMAGE_GIF);
        fastMediaTypes.add(MediaType.IMAGE_JPEG);
        fastMediaTypes.add(MediaType.IMAGE_PNG);
        fastMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        fastMediaTypes.add(MediaType.TEXT_HTML);
        fastMediaTypes.add(MediaType.TEXT_MARKDOWN);
        fastMediaTypes.add(MediaType.TEXT_PLAIN);
        fastMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
//        将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }
}
