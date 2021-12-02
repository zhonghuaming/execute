package cn.huaming.flink.time;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @auther: ZHM
 * @date: 2021/12/2 10:41
 */
@Slf4j
public class EventTimeWmTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        test2(env);
        env.execute();

    }

    /**
     * 时间字段，单词，次数
     * 根据时间段，统计滚动窗口间出现单词的频率。
     *
     * @param env
     * @return
     */
    public static void test1(StreamExecutionEnvironment env) {

        DataStreamSource<String> dataStream = env.socketTextStream("127.0.0.1", 9999);
        SingleOutputStreamOperator<String> operator = dataStream
                .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<String>(Time.seconds(0)) {
                    @Override
                    public long extractTimestamp(String element) {
                        long l = Long.parseLong(element.split(",")[0]);
                        log.debug("获取时间：{}", l);
                        return l;
                    }
                });
        SingleOutputStreamOperator<Tuple2<String, Integer>> map = operator.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) {
                String[] split = s.split(",");
                log.debug("截取数组：{}", JSON.toJSONString(split));
                return Tuple2.of(split[1].trim(), Integer.parseInt(split[2].trim()));
            }
        });

        map.keyBy(x -> {
                            log.debug("fo:{}", x.f0);
                            log.debug("f1:{}", x.f1);
                            return x.f0;
                        }
                ).window(TumblingEventTimeWindows.of(Time.seconds(5))).sum(1)
                .print();

    }

    /**
     * 时间字段，单词，次数
     * 根据时间段，统计滚动窗口间出现单词的频率。
     *
     * @param env
     * @return
     */
    public static void test2(StreamExecutionEnvironment env) {

        DataStreamSource<String> dataStream = env.socketTextStream("127.0.0.1", 9999);
        SingleOutputStreamOperator<String> operator = dataStream
                .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<String>(Time.seconds(0)) {
                    @Override
                    public long extractTimestamp(String element) {
                        long l = Long.parseLong(element.split(",")[0]);
                        log.debug("获取时间：{}", l);
                        return l;
                    }
                });
        SingleOutputStreamOperator<Tuple2<String, Integer>> map = operator.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) {
                String[] split = s.split(",");
                log.debug("截取数组：{}", JSON.toJSONString(split));
                return Tuple2.of(split[1].trim(), Integer.parseInt(split[2].trim()));
            }
        });

        map.keyBy(x -> {
                            log.debug("fo:{}", x.f0);
                            log.debug("f1:{}", x.f1);
                            return x.f0;
                        }
                ).window(TumblingEventTimeWindows.of(Time.seconds(5))).reduce(new ReduceFunction<Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                        log.debug("value1：{}", JSON.toJSONString(value1));
                        log.debug("value2：{}", JSON.toJSONString(value2));
                        return Tuple2.of(value1.f0, value1.f1 + value2.f1);
                    }
                }, new ProcessWindowFunction<Tuple2<String, Integer>, Object, String, TimeWindow>() {
                    @Override
                    public void process(String s, ProcessWindowFunction<Tuple2<String, Integer>, Object, String, TimeWindow>.Context context, Iterable<Tuple2<String, Integer>> elements, Collector<Object> out) throws Exception {
                        log.debug("-------------ProcessWindowFunction start----------------");
                        FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
                        for (Tuple2<String, Integer> element : elements) {
                            out.collect(instance.format(element.f1));
                        }
                        log.debug("当前处理时间:{}",context.currentProcessingTime());
                        TimeWindow window = context.window();
                        log.debug("窗口开始时间:{}",window.getStart());
                        log.debug("窗口结束时间:{}",window.getEnd());
                        log.debug("-------------ProcessWindowFunction end----------------");
                    }
                })
                .print();

    }


}
