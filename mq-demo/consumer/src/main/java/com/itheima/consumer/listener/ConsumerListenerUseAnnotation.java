package com.itheima.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author luruoyang
 */
//@Component
@Slf4j
public class ConsumerListenerUseAnnotation {

  /**
   *
   */
  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "direct.queue1"),
          exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
          key = {"red", "blue"}

      )
  )
  public void listenDirectQueue1(String msg) {
    System.out.println("消费者1接收到direct.queue1的消息: " + msg);
  }

  /**
   *
   */
  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "direct.queue2"),
          exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
          key = {"red", "yellow"}
      )
  )
  public void listenDirectQueue2(String msg) {
    System.out.println("消费者2接收到direct.queue2的消息: " + msg);
  }
}
