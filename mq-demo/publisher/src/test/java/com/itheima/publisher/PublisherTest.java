package com.itheima.publisher;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author luruoyang
 */
@SpringBootTest
@Slf4j
public class PublisherTest {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  @DisplayName("简单队列模型")
  public void testSimpleMode() throws Exception {
    String msg = "hello rabbit";
    for (int i = 0; i < 10; i++) {
      TimeUnit.SECONDS.sleep(1);
      rabbitTemplate.convertAndSend("simple.queue", msg + LocalDateTime.now());
    }
  }

  @Test
  @DisplayName("工作队列模型")
  public void testWorkerQueueMode() throws Exception {
    String msg = "hello msg";
    for (int i = 0; i < 100; i++) {
      rabbitTemplate.convertAndSend("work.queue", msg + i);
    }
  }

  @Test
  @DisplayName("FanOut Mode, 广播模式")
  public void testFanOutMode() throws Exception {
    String msg = "hello msg";
    for (int i = 0; i < 2; i++) {
      rabbitTemplate.convertAndSend("fanout.exchange", null, msg + i);
    }
  }

  @Test
  @DisplayName("Direct Mode, 直接路由模式")
  public void testDirectMode() throws Exception {
    String msg = "hello msg";
    for (int i = 0; i < 2; i++) {
      rabbitTemplate.convertAndSend("hmall.direct", "yellow", msg + i);
    }
  }

  @Test
  @DisplayName("Topic Mode, 主题匹配模式")
  public void testTopicMode() throws Exception {
    String msg = "hello msg";
    for (int i = 0; i < 1; i++) {
      rabbitTemplate.convertAndSend("hmall.topic", "china.news", msg + i);
    }
  }

  @Test
  @DisplayName("Object Message, 对象消息")
  public void testObjectMessage() throws Exception {
    Map<String, Object> person = new HashMap<>();
    person.put("name", "jack");
    person.put("age", 29);
    person.put("addr", "河南省郑州市");
    rabbitTemplate.convertAndSend("object.queue", person);
  }

  @Test
  @DisplayName("Lazy Queue")
  public void testLazyQueue() {

  }

  @Test
  @DisplayName("Priority Queue")
  public void testPriorityQueue() throws Exception {
    String queueName = "priority.queue";
    for (int i = 0; i < 100; i++) {
      int priority = RandomUtil.randomInt(1, 10);
      String msg = "test msg" + i + "priority: " + priority;
      log.info(" msg is : {}", msg);
      rabbitTemplate.convertAndSend(queueName, msg, message -> {
        message.getMessageProperties().setPriority(priority);
        return message;
      });
    }
  }

}
