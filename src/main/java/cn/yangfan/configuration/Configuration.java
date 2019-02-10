package cn.yangfan.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("cn.yangfan.dao")
@SpringBootApplication
@ComponentScan(basePackages="cn.yangfan")
public class Configuration {
	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}
}
