package com.itheima.publisher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.itheima.publisher", "com.itheima.common.rabbitmq"})
@MapperScan("com.itheima.common.rabbitmq.dao.mapper")
public class PublisherApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class);
    }
}
