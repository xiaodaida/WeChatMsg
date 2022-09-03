package org.example.schedule;

import org.example.api.requestresult.gaode.WeatherInput;
import org.example.api.requestresult.tianxing.constellation.ConstellationInput;
import org.example.wechat.templateservice.TemplateService;
import org.example.wechat.token.WeChatToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName WeChatSchedule
 * @Description TODO
 * @date 2022/9/3 17:00
 * @Version 1.0
 * @Author liukai
 */
@Component
public class WeChatSchedule {

    @Autowired
    private WeChatToken weChatToken;
    @Autowired
    private TemplateService templateService;

    /**
     * 获取微信access_token， 自动一个小时获取一次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getAccessToken() {
        weChatToken.getAccessToken();
    }

    @Scheduled(cron = "0 30 8 * * ?")
    public void sendWeatherAndConsMsg(){
        WeatherInput weatherInput = new WeatherInput();
        ConstellationInput constellationInput = new ConstellationInput();
        weatherInput.setUserId("");// 微信观众用户userid
        weatherInput.setCity("330108");
        constellationInput.setAstro("");// 星座
        templateService.sendWeatherAndCons(weatherInput, constellationInput);
    }
}
