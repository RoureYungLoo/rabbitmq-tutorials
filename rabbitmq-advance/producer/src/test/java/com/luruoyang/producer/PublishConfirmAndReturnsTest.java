package com.luruoyang.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
@DisplayName("生产者确认机制")
public class PublishConfirmAndReturnsTest {

  @Resource
  private RabbitTemplate rabbitTemplate;

  @Test
  @DisplayName("测试生产者Confirm和Returns机制")
  public void testPublishConfirmAndReturns() {
    String exchangeName = "hmall.direct11";
    String routingKey = "blue1";
    String msg = "hello, msg for testing publish confirm and returns";

    /* 注册 Confirm 回调函数 */
    /*rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
      @Override
      public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
          log.info("confirm 成功, 消息成功发送到 Exchange");
        } else {
          log.error("confirm 失败, 消息发送到 Exchange 失败: {}", cause);
        }
      }
    });*/

    /* 注册 Returns 回调函数 */
    /*rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
      @Override
      public void returnedMessage(ReturnedMessage returned) {
        log.error("returns 失败, 消息发送到 Queue 失败: {}", returned.getReplyText());
      }
    });*/

    rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
  }
}