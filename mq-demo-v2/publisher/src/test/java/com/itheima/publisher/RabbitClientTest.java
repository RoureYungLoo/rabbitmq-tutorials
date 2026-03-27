package com.itheima.publisher;

import com.itheima.common.rabbitmq.client.RabbitClient;
import lombok.extern.slf4j.Slf4j;
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
  public void testPublishReturn() throws InterruptedException {
    rabbitClient.sendMsg("hmall.direct", "blue1", "hello message");
    Thread.sleep(2000);
  }
}
