package com.itheima.publisher.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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

  @Bean
  public Queue ttlQueue() {
    Queue queue = QueueBuilder.durable("ttl.queue")
        .build();

    return null;
  }

//    @Bean
//    public Queue ttlQueue(){
//        return null;
//    }

//    @Bean
//    public DirectExchange directExchange(){
//        return  new DirectExchange();
//    }

//    @Bean
//    public Binding binding(){
//        BindingBuilder.bind().to(directExchange()).with("ttl");
//    }
}
