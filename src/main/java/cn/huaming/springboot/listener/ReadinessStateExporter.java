package cn.huaming.springboot.listener;

import static org.springframework.boot.availability.ReadinessState.ACCEPTING_TRAFFIC;
import static org.springframework.boot.availability.ReadinessState.REFUSING_TRAFFIC;

import cn.huaming.springboot.properties2bean.AcmeProperties;
import cn.huaming.springboot.properties2bean.BcmeProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ReadinessStateExporter {

    @Autowired
    private AcmeProperties acmeProperties;
    @Autowired
    private BcmeProperties bcmeProperties;

    @EventListener
    /**
     * 监听系统消息，
     * AvailabilityChangeEvent类型的消息都从会触发此方法被回调
     * @param event
     */
    public void onStateChange(AvailabilityChangeEvent<? extends AvailabilityState> event) {
        acmeProperties.getRemoteAddress();
        bcmeProperties.getRemoteAddress();
        AvailabilityState state = event.getState();
        if (ACCEPTING_TRAFFIC.equals(state)) {
            log.trace(ACCEPTING_TRAFFIC + "");
        } else if (REFUSING_TRAFFIC.equals(state)) {
            log.trace(REFUSING_TRAFFIC + "");
        }
    }

}