package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于RabbitMQ API, 通过注入Bean的方式创建 Queue 和 Exchange
 *
 * @author luruoyang
 */
@Configuration
public class DirectConfig {

  /**
   * 注入 Exchange
   */
  @Bean
  public DirectExchange directExchange() {
    return ExchangeBuilder.directExchange("taobao.direct").build();
  }

  /**
   * 注入 Queue 1
   */
  @Bean
  public Queue directQueue1() {
    return new Queue("taobao.direct.queue1");
  }

  /**
   * 绑定 队列 和 交换机
   */
  @Bean
  public Binding bindingQueue1WitherRed() {
    return BindingBuilder.bind(directQueue1()).to(directExchange()).with("red");
  }

  /**
   * 绑定 队列 和 交换机
   */
  @Bean
  public Binding bindingQueue1WithBlue() {
    return BindingBuilder.bind(directQueue1()).to(directExchange()).with("blue");
  }

  /**
   * 注入 Queue 2
   */
  @Bean
  public Queue directQueue2() {
    return new Queue("taobao.direct.queue2");
  }

  /**
   * 绑定 Exchange 与 Routing Key
   */
  @Bean
  public Binding bindingQueue2WithRed() {
    return BindingBuilder.bind(directQueue2()).to(directExchange()).with("red");
  }

  /**
   * 绑定 Exchange 与 Routing Key
   */
  @Bean
  public Binding bindingQueue2WithYellow(Queue directQueue2, DirectExchange directExchange) {
    return BindingBuilder.bind(directQueue2).to(directExchange).with("yellow");
  }
}
