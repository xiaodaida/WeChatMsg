package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName org.example.WeChatMsgStarter
 * @Description TODO
 * @date 2022/9/3 14:12
 * @Version 1.0
 * @Author liukai
 */
@SpringBootApplication
@EnableScheduling
public class WeChatMsgStarter {

    public static void main(String[] args) {
        SpringApplication.run(WeChatMsgStarter.class, args);
    }
}
