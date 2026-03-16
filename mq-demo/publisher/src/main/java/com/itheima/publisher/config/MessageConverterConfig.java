package com.itheima.publisher.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luruoyang
 */
@Configuration
public class MessageConverterConfig {

  /**
   * 自定义消息转换器
   */
  @Bean
  public MessageConverter messageConverter() {
    Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
    messageConverter.setCreateMessageIds(true);
    return messageConverter;
  }

}
