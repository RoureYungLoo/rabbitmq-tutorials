package com.itheima.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于RabbitMQ API, 通过注入Bean的方式创建 Queue 和 Exchange
 *
 * @author luruoyang
 */
@Configuration
public class FanoutConfig {

  /**
   * 注入 Exchange
   */
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("taobao.fanout");
  }

  /**
   * 注入 Queue 1
   */
  @Bean
  public Queue fanoutQueue1() {
    return new Queue("taobao.fanout.queue1");
  }

  /**
   * 注入 Queue 2
   */
  @Bean
  public Queue fanoutQueue2() {
    return new Queue("taobao.fanout.queue2");
  }

  /**
   * 注入 绑定: Queue 1 绑定到 Exchange
   */
  @Bean
  public Binding bindingQueue1() {
    return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
  }

  /**
   * 注入 绑定: Queue 2 绑定到 Exchange
   */
  @Bean
  public Binding bindingQueue2() {
    return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
  }
}
