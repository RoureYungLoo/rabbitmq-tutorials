package com.luruoyang.producer;

import com.luruoyang.common.client.RabbitClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author luruoyang
 */
@SpringBootTest
@Slf4j
public class RabbitClientTest {

  @Resource
  private RabbitClient rabbitClient;

  @Test
  @DisplayName("测试消息失败表")
  public void testPublishConfirmReturn() throws InterruptedException {
    String exchange = "hmall.direct111";
    String routingKey = "blue";
    String msg = "hello message for testing MQ failure table";
    rabbitClient.sendMsg(exchange, routingKey, msg);
    Thread.sleep(500);
  }
}
