package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luruoyang
 */
@Configuration
public class TopicConfig {

  /**
   * 注入 Exchange
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("taobao.topic");
  }

  /**
   * 注入 Topic Queue 1
   */
  @Bean
  public Queue topicQueue1() {
    return QueueBuilder.durable("taobao.topic.queue1").build();
  }

  /**
   * 注入 Topic Queue 2
   */
  @Bean
  public Queue topicQueue2() {
    return QueueBuilder.durable("taobao.topic.queue2").build();
  }

  /**
   * 绑定 队列 到 交换机 Topic key
   */
  @Bean
  public Binding bindingTopicQueue1() {
    return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("china.#");
  }

  /**
   * 绑定 队列 到 交换机 Topic key
   */
  @Bean
  public Binding bindingTopicQueue2() {
    return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("*.news");
  }
}
