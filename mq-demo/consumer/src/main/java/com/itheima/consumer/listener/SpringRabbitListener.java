package com.itheima.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author luruoyang
 */
@Component
@Slf4j
public class SpringRabbitListener {

  @RabbitListener(queues = {"simple.queue"})
  public void listenSimpleQueueMessage(String msg) throws Exception {
    log.warn("spring消费者接受到的消息：{}", msg);
  }

  @RabbitListener(queues = {"work.queue"})
  public void workQueueModeListener1(String msg) throws InterruptedException {
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    log.info("工作队列模式 MQ消费者1 接收到消息: {}", String.format("[%s] %s", msg, now));
    Thread.sleep(20);
  }

  @RabbitListener(queues = {"work.queue"})
  public void workQueueModeListener2(String msg) throws InterruptedException {
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    log.info("工作队列模式 MQ消费者2 接收到消息: {}", String.format("[%s] %s", msg, now));
    Thread.sleep(200);
  }

  /**
   * 发布订阅模型 广播模式
   */
  @RabbitListener(queues = "fanout.queue1")
  public void publishSubscribeFanoutListener1(String msg) {
    log.info("Pub/Sub Fanout 消费者1收到消息: [{}]", msg);
  }

  @RabbitListener(queues = "fanout.queue2")
  public void publishSubscribeFanoutListener2(String msg) {
    log.info("Pub/Sub Fanout 消费者2收到消息: [{}]", msg);
  }

  /**
   * 发布订阅模型 直接(路由)模式
   */
  @RabbitListener(queues = {"direct.queue1"})
  public void publishSubscribeDirectListener1(String msg) {
    log.info("Pub/Sub Direct 消费者1(red,blue)收到消息: [{}]", msg);
  }

  @RabbitListener(queues = {"direct.queue2"})
  public void publishSubscribeDirectListener2(String msg) {
    log.info("Pub/Sub Direct 消费者2(red,yellow)收到消息: [{}]", msg);
  }

  /**
   * 发布订阅模型 主题(匹配)模式
   */
  @RabbitListener(queues = {"topic.queue1"})
  public void publishSubscribeTopicListener1(String msg) {
    log.info("Pub/Sub Topic(china.#) 消费者1收到消息: [{}]", msg);
  }

  @RabbitListener(queues = {"topic.queue2"})
  public void publishSubscribeTopicListener2(String msg) {
    log.info("Pub/Sub Topic(#.news) 消费者2收到消息: [{}]", msg);
  }
}
