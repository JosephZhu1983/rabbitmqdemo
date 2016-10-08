package me.josephzhu.springrabbitmqdemo;

import me.ele.contract.exception.ServiceException;
import me.ele.elog.Log;
import me.ele.elog.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhuye on 05/10/2016.
 */
public class DemoMessageListener extends AbstractMessageListener<DemoMessage>{

    private static final Log logger = LogFactory.getLog(DemoMessageListener.class);
    public static LongAdder longAdder = new LongAdder();
    protected void process(DemoMessage msg) throws ServiceException {
        longAdder.increment();
    }
}
