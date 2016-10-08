package me.josephzhu.springrabbitmqdemo;

import me.ele.elog.Log;
import me.ele.elog.LogFactory;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by zhuye on 05/10/2016.
 */
public class DemoAppListener implements ApplicationListener<ListenerContainerConsumerFailedEvent> {
    private static final Log logger = LogFactory.getLog(DemoAppListener.class);

    public void onApplicationEvent(ListenerContainerConsumerFailedEvent listenerContainerConsumerFailedEvent)
    {
        logger.warn("监听到ListenerContainerConsumerFailedEvent={}", listenerContainerConsumerFailedEvent);
    }
}
