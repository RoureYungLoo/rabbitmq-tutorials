package com.itheima.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Map;

/**
 * @author luruoyang
 */
//@Component
@Slf4j
public class ConsumerListener {

  /**
   * 简单队列消费者
   */
  @RabbitListener(queues = "simple.queue")
  public void simpleListener(String msg) {
    log.info("简单模型消费到消息: {}", msg);
  }

  @RabbitListener(queues = "work.queue")
  public void simpleListener1(String msg) {

//    try {
//      TimeUnit.MILLISECONDS.sleep(20);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }

    log.info("Work Queue [1] 消费到消息: {}", msg);
  }

  @RabbitListener(queues = "work.queue")
  public void simpleListener2(String msg) {

//    try {
//      TimeUnit.MILLISECONDS.sleep(60);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }

    log.info("Work Queue [2] 消费到消息: {}", msg);
  }

  @RabbitListener(queues = "fanout.queue1")
  public void fanOutListener1(String msg) {
    log.info("Fan Out Mode 消费者[1]到消息: {}", msg);
  }

  @RabbitListener(queues = "fanout.queue2")
  public void fanOutListener2(String msg) {
    log.info("Fan Out Mode 消费者[2]到消息: {}", msg);
  }

  @RabbitListener(queues = "direct.queue1")
  public void directQueue1Listener1(String msg) {
    log.info("Direct Mode direct.queue1 消费者[1]到消息: {}", msg);
  }

  @RabbitListener(queues = "direct.queue2")
  public void directQueue2Listener1(String msg) {
    log.info("Direct Mode direct.queue2 消费者[1]到消息: {}", msg);
  }

  @RabbitListener(queues = "topic.queue1")
  public void topicQueue1Listener1(String msg) {
    log.info("Topic Mode topic.queue1 消费者[1]到消息: {}", msg);
  }

  @RabbitListener(queues = "topic.queue2")
  public void topicQueue2Listener1(String msg) {
    log.info("Topic Mode topic.queue2 消费者[1]到消息: {}", msg);
  }

  @RabbitListener(queues = "object.queue")
  public void listenObjectMessage(Map<String, Object> msg) {
    System.out.println("object.queue 消费者收到消息: " + msg);
  }

  /**
   * 监听 LazyQueue
   */
  @RabbitListener(
      queuesToDeclare = @Queue(
          name = "lazy.queue",
          durable = "true",
          arguments = @Argument(name = "x-queue-mode", value = "lazy")
      )
  )
  public void listenLazyQueue(String msg) throws Exception {
    System.out.println("LazyQueue 消费者收到消息: " + msg);
  }

  /**
   * 监听 Priority Queue
   */
  @RabbitListener(
      queuesToDeclare = @Queue(
          name = "priority.queue",
          durable = "true",
          arguments = @Argument(
              name = "x-max-priority",
              value = "10",
              type = "java.lang.Integer"
          )
      )
  )
  public void listenPriorityQueue(String msg) {
    System.out.println("Priori`tyQueue 消费者收到消息: " + msg);
  }

}
