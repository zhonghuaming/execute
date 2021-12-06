package cn.huaming.flink.project;

import cn.huaming.flink.entity.Event;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 统计不同事件类型、类别、商品的Top n 访问量
 * Siding Window + Watermarks
 * 窗口大小5m,滑动步长1m
 *
 * @auther: ZHM
 * @date: 2021/12/6 16:56
 */

@Slf4j
public class TopTest {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.readTextFile("E:\\workspace\\huaming\\test\\src\\main\\resources\\data-file\\event.log")
                .map(new MapFunction<String, Event>() {
                    @Override
                    public Event map(String value) {
                        try {
                            return JSON.parseObject(value, Event.class);
                        } catch (Exception e) {
                            log.error("value:{}", value);
                        }
                        return null;
                    }
                })
                .print();
    }
}
