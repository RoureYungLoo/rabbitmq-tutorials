package com.itheima.publisher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 注意: 需要先注释掉com.itheima.common.rabbitmq.config.RabbitMqConfiguration 下的rabbitTemplate.setReturnsCallback()代码段, 否则会报错
 *
 * @author luruoyang
 */
@SpringBootTest
@Slf4j
public class PublishConfirmAndReturnTest {
  @Resource
  private RabbitTemplate rabbitTemplate;

  String exchangeName = "hmall.direct"; /* no exchange 'hmall.direct1' in vhost '/hmall' */
  String message = "红色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";

  @Test
  @DisplayName("publish confirm")
  public void testPublishConfirm() {

    /* publish confirm: Exchange */
    rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
      @Override
      public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
          log.info("消息发送到交换机成功");
        } else {
          log.info("消息发送到交换机失败: {}", cause);
        }
      }
    });

    /* publish return: Queue */
    rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
      @Override
      public void returnedMessage(ReturnedMessage returned) {
        log.error("发送消息到队列失败: {}", returned.getReplyText()); // NO_ROUTE
      }
    });
    rabbitTemplate.convertAndSend(exchangeName, "blue", message);
  }

//  @DisplayName("publish return")
//  @Test
//  public void testPublishReturn() {
//
//  /* publish return: Queue */
//    rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//    @Override
//    public void returnedMessage(ReturnedMessage returned) {
//      log.error("发送消息到队列失败: {}", returned.getReplyText()); // NO_ROUTE
//    }
//  });
//    rabbitTemplate.convertAndSend(exchangeName, "blue1", message);
//  }
}
