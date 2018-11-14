package cn.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = {"cn.controller","cn.service","cn.serviceimpl","cn.utils","cn.config","cn.ctr.api"})
@MapperScan(basePackages={ "cn.dao" })
@EnableAutoConfiguration
@EnableScheduling
@EnableSwagger2
public class Application {
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*@Scheduled(initialDelay = 50000,fixedRate = 6000)
    public void getToken() {
        System.out.println("getToken定时任务启动:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }*/
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configure(builder);
	}
}
