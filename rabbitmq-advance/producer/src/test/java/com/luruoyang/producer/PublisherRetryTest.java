package com.luruoyang.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author luruoyang
 */
@SpringBootTest
@Slf4j
@DisplayName("生产者重试机制")
public class PublisherRetryTest {

  @Resource
  private RabbitTemplate rabbitTemplate;


  @Test
  @DisplayName("测试生产者重试")
  public void testPublisherRetry() {
    String exchangeName = "hmall.direct";
    String msg = "hello, msg for publisher retry";
    rabbitTemplate.convertAndSend(exchangeName, "blue", msg);
  }
}
