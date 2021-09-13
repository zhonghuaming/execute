package cn.huaming.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaController {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("/send/{msg}")
    public void sendMessage(@PathVariable("msg") String msg) {
        kafkaSender.send(msg);
    }
}