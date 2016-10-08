package me.josephzhu.springrabbitmqdemo;

import me.ele.elog.Log;
import me.ele.elog.LogFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhuye on 05/10/2016.
 */
@Service
public class DemoMessageSender {
    private static final Log logger = LogFactory.getLog(DemoMessageSender.class);

    @Autowired
    private AmqpTemplate demoRabbitMQTemplate;

    public static LongAdder longAdder = new LongAdder();


    public void sendMessage() {
        DemoMessage demoMessage = new DemoMessage(1L, UUID.randomUUID().toString(), 10.2);

        try {
            demoRabbitMQTemplate.convertAndSend("aa", demoMessage);
            longAdder.increment();
            //logger.info("发出消息成功:{}", demoMessage);
        }
        catch (Exception ex) {
            logger.error("发出消息失败:{}", demoMessage, ex);

        }

    }

    public void sendDelayedMessage()
    {
        DemoMessage demoMessage = new DemoMessage(1L, UUID.randomUUID().toString(), 10.2);

        demoRabbitMQTemplate.convertAndSend("aa",demoMessage, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(5000);
                return message;
            }
        });

        logger.info("发出消息成功:{}", demoMessage);

    }
}
