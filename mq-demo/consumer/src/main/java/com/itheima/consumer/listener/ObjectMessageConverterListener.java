package com.itheima.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author luruoyang
 */
@Component
@Slf4j
public class ObjectMessageConverterListener {
  @RabbitListener(queues = "object.queue")
  public void listener(Map<String, Object> msg) {
    log.info("收到消息: {}", msg);
  }
}
