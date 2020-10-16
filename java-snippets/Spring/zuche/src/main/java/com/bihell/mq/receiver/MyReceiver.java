package com.bihell.mq.receiver;

import com.bihell.mq.common.RabbitConfig;
import com.bihell.mq.model.MyModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MyReceiver {
    @RabbitHandler
    public void process(MyModel model) {
        System.out.println("接收处理队列A当中的消息： " + model.getInfo());
    }
}