package me.josephzhu.springrabbitmqdemo;

import com.rabbitmq.client.Channel;
import me.ele.contract.exception.ServiceException;
import me.ele.elog.Log;
import me.ele.elog.LogFactory;
import me.ele.lpd.core.metric.MetricUtils;
import me.ele.lpd.core.util.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.lang.reflect.ParameterizedType;


public abstract class AbstractMessageListener<T> implements ChannelAwareMessageListener {

    private static final Log logger = LogFactory.getLog(AbstractMessageListener.class);

    public void onMessage(Message message, Channel channel) throws Exception {
        String messageBody = new String(message.getBody());
        //所有收到的消息记录一下
        logger.info("{}监听到消息message={}", this.getClass().getName(),  messageBody);
        //收到消息后直接打点
        MetricUtils.recordSuccessMetric(this.getClass(), "onMessage");

        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();

        T messageEntity = JsonUtils.fromJson(messageBody, (Class<T>) type.getActualTypeArguments()[0]);
        long start = System.currentTimeMillis();
        try {
            process(messageEntity);
            //执行业务逻辑成功后打点
            MetricUtils.recordTimeMetric(this.getClass(), "onMessage_success", start);
        } catch (Exception e) {
            //业务逻辑执行失败记录一下
            logger.error("{}处理消息出错message={}", this.getClass().getName(), messageBody, e);
            //执行业务逻辑失败后打点
            MetricUtils.recordTimeMetric(this.getClass(), "onMessage_failure", start);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    protected abstract void process(T msg) throws ServiceException;
}
