package com.itheima.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 使用注解方式, 创建 Queue , Exchange, Binding
 *
 * @author luruoyang
 */
@Component
@Slf4j
public class UseWithAnnotation {

  /**
   * Pub/Sub Direct
   */
  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "jd.direct.queue1"),
          exchange = @Exchange(name = "jd.direct", type = ExchangeTypes.DIRECT),
          key = {"red", "blue"}
      )
  )
  public void publishSubscribeDirectListener1(String msg) {
    log.info("listener1 received msg from jd.direct.queue1 : {}", msg);
  }

  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "jd.direct.queue2"),
          exchange = @Exchange(name = "jd.direct", type = ExchangeTypes.DIRECT),
          key = {"red", "yellow"}
      )
  )
  public void publishSubscribeDirectListener2(String msg) {
    log.info("listener1 received msg from jd.direct.queue2 : {}", msg);
  }

  /**
   * Pub/Sub Topic
   */

  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "tianmao.queue1"),
          exchange = @Exchange(name = "tianmao.topic", type = ExchangeTypes.TOPIC),
          key = {"china.#"}
      )
  )
  public void publishSubscribeTopicListener1(String msg) {
    log.info("Pub/Sub Topic Listener1 received: {}", msg);
  }

  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "tianmao.queue2"),
          exchange = @Exchange(name = "tianmao.topic", type = ExchangeTypes.TOPIC),
          key = {"#.news"}
      )
  )
  public void publishSubscribeTopicListener2(String msg) {
    log.info("Pub/Sub Topic Listener2 received: {}", msg);
  }

  /**
   * 延迟队列, Lazy Queue
   */
  @RabbitListener(
      queuesToDeclare = @Queue(
          name = "lazy.queue",
          durable = "true",
          arguments = @Argument(
              name = "x-queue-mode", value = "lazy"
          )
      )
  )
  public void lazyQueueListener(String msg) {
    log.info("lazy.queue 的 消费者接收到消息: {}", msg);
  }

  /**
   * 优先级队列, Priority Queue
   */
  @RabbitListener(queuesToDeclare = @Queue(
      name = "priority.queue",
      durable = "true",
      arguments = @Argument(
          /* 设置队列参数x-max-priority=10 */
          name = "x-max-priority", value = "10", type = "java.lang.Integer"
      )
  ))
  public void priorityQueueListener(String msg) {
    log.info("priority queue 的 listener 接收到 msg : {}", msg);
  }
}
