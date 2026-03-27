package com.luruoyang.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luruoyang
 */
@SpringBootApplication(scanBasePackages = {
    "com.luruoyang.producer", "com.luruoyang.common"
})
@MapperScan(basePackages = {"com.luruoyang.common.dao.mapper"})
public class ProducerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }
}
