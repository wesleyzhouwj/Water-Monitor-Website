package cn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingConfig {
	

    

    //@Scheduled(cron = "0 0/1 * * * ?") // 每10分钟执行一次
	//初始化后5秒执行，之后每隔6秒执行一次
    @Scheduled(initialDelay = 5000,fixedRate = 6000*10)
    public void getToken() {
        //System.out.println("getToken定时任务启动:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
