package com.itheima.publisher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author luruoyang
 */
@SpringBootTest
@DisplayName("消息转换器")
public class ObjectMessageConverterTest {

  @Resource
  private RabbitTemplate rabbitTemplate;

  @Test
  @DisplayName("发送对象类型的消息")
  public void testObjectMessageConverter() {
    HashMap<String, Object> msg = new HashMap<>();
    msg.put("name", "张三");
    msg.put("addr", "河南省郑州市");
    rabbitTemplate.convertAndSend("object.queue", msg);
  }

}
