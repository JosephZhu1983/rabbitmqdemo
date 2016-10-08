package me.josephzhu.springrabbitmqdemo;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhuye on 05/10/2016.
 */
@Service
public class Mainer {

    @Autowired
    private DemoMessageSender demoMessageSender;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-rabbit.xml");

//        new Thread(()->{
//
//            long read = 0;
//            long write = 0;
//
//            CachingConnectionFactory readConnectionFactory = (CachingConnectionFactory)context.getBean("rabbitReadConnectionFactory");
//            CachingConnectionFactory writeConnectionFactory = (CachingConnectionFactory)context.getBean("rabbitWriteConnectionFactory");
//
//
//            while (true) {
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(String.format("Write tps:%d, Read tps:%d", (DemoMessageSender.longAdder.longValue() - write) / 10,
//                        (DemoMessageListener.longAdder.longValue() -read) / 10));
//
//                System.out.println(String.format("Write CF:%s, Read CF:%s", writeConnectionFactory.getCacheProperties(), readConnectionFactory.getCacheProperties()));
//
//                        read = DemoMessageListener.longAdder.longValue();
//                write = DemoMessageSender.longAdder.longValue();
//
//            }
//
//        }).start();


        Mainer mainer = context.getBean(Mainer.class);
        mainer.sendMessages();
    }

    private void sendMessages()
    {
        for(int i=0;i<1;i++) {
            new Thread(()-> {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demoMessageSender.sendDelayedMessage();
                }
            }).start();
        }
    }
}
