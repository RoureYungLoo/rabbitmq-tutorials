package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2024/10/12 11:08
 */
@Configuration
public class MesaageConfig {

  //定义消息转换器
  @Bean
  public MessageConverter messageConverter() {
    Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
    //自动设置消息id
    jackson2JsonMessageConverter.setCreateMessageIds(true);
    return jackson2JsonMessageConverter;
  }

  // 交换机
//  @Bean
//  public DirectExchange directExchange() {
//    Exchange exchange = ExchangeBuilder.directExchange("direct.exchange").durable(true).build();
//    return new DirectExchange("direct.exchange");
//     return (DirectExchange) exchange;
//  }

  // 队列
//  @Bean
//  public Queue directQueue() {
//    Queue queue = QueueBuilder.durable("direct.queue").build();
//    return queue;
//  }

  // 带有Exchange
  @Bean
  public DirectExchange ttlDirectExchange() {
    Exchange ttlExchange = ExchangeBuilder.directExchange("ttl.fanout").durable(true).build();
    return (DirectExchange) ttlExchange;
  }

  // 带有 TTL 的 Queue
  @Bean
  public Queue ttlQueue() {
    Queue queue = QueueBuilder.durable("ttl.queue")
        .ttl(10000)
        .deadLetterExchange("hmall.direct")
        .deadLetterRoutingKey("blue")
        .build();
    return queue;
  }

  // 绑定
  @Bean
  public Binding bindTtlQueue() {
    Binding binding = BindingBuilder
        .bind(ttlQueue())
        .to(ttlDirectExchange())
        .with("ttl");
    return binding;
  }
}
