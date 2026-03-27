package com.luruoyang.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author luruoyang
 */
@Component
@Slf4j
public class PubSubFanoutLister {

  @RabbitListener(queues = {"direct.queue1"})
  public void pubSubFanoutListener1(String msg) {
    log.info("direct.queue1 listener received msg: {}", msg);
  }

  @RabbitListener(queues = {"direct.queue2"})
  public void pubSubFanoutListener2(String msg) {
    log.info("direct.queue2 listener received msg: {}", msg);
  }
}
