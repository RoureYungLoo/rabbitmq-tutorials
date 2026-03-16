package com.itheima.publisher;

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
@DisplayName("发布订阅模型测试")
public class ModePublishSubscribeTest {

  @Resource
  private RabbitTemplate rabbitTemplate;

  @Test
  @DisplayName("扇形广播交换机测试")
  public void PublishSubscribeFanoutTest() {
    String exchangeName = "hmall.fanout";
    String msg = "hello, message_";
    for (int i = 0; i < 5; i++) {
      rabbitTemplate.convertAndSend(exchangeName, "", msg + i);
    }
  }

  @Test
  @DisplayName("直接路由交换机测试")
  public void PublishSubscribeDirectTest() {
    String exchangeName = "hmall.direct";
    for (String key : new String[]{"red", "blue", "yellow"}) {
      String routingKey = key;
      String msg = String.format("这是一条%s消息...", key);
      rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
    }
  }

  @Test
  @DisplayName("主题匹配交换机测试")
  public void PublishSubscribeTopicTest() {
    String exchangeName = "hmall.topic";
    String topic = "china.weather.news";
    String msg = "这是一条消息...";
    rabbitTemplate.convertAndSend(exchangeName, topic, msg);
  }
}
