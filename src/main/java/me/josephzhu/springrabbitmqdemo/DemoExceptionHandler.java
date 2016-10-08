package me.josephzhu.springrabbitmqdemo;

import com.rabbitmq.client.*;
import me.ele.elog.Log;
import me.ele.elog.LogFactory;

import java.util.Date;

/**
 * Created by zhuye on 05/10/2016.
 */
public class DemoExceptionHandler implements ExceptionHandler
{
    private static final Log logger = LogFactory.getLog(DemoExceptionHandler.class);

    public void handleUnexpectedConnectionDriverException(Connection conn, Throwable exception)
    {
        logger.error("handleUnexpectedConnectionDriverException conn:{}",conn, exception);
    }

    public void handleReturnListenerException(Channel channel, Throwable exception)
    {
        logger.error("handleReturnListenerException channel:{}",channel, exception);

    }

    public void handleFlowListenerException(Channel channel, Throwable exception)
    {
        logger.error("handleFlowListenerException channel:{}",channel, exception);

    }

    public void handleConfirmListenerException(Channel channel, Throwable exception)
    {
        logger.error("handleConfirmListenerException channel:{}",channel, exception);

    }

    public void handleBlockedListenerException(Connection connection, Throwable exception)
    {
        logger.error("handleUnexpectedConnectionDriverException conn:{}",connection, exception);

    }

    public void handleConsumerException(Channel channel, Throwable exception, Consumer consumer, String consumerTag, String methodName)
    {
        logger.error("handleConsumerException channel:{} consumer:{} methodName:{}",channel, consumer, methodName, exception);

    }

    public void handleConnectionRecoveryException(Connection conn, Throwable exception)
    {
        logger.error("handleConnectionRecoveryException conn:{}",conn, exception);

    }

    public void handleChannelRecoveryException(Channel ch, Throwable exception)
    {
        logger.error("handleChannelRecoveryException channel:{}",ch, exception);
    }

    public void handleTopologyRecoveryException(Connection conn, Channel ch, TopologyRecoveryException exception)
    {
        logger.error("handleTopologyRecoveryException conn:{}",conn, exception);

    }
}

