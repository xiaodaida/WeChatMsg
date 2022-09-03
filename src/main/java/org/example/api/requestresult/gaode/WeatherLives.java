package org.example.api.requestresult.gaode;

import lombok.Data;

/**
 * @ClassName WeatherLives
 * @Description 高德地图天气api
 * @date 2022/8/25 21:03
 * @Version 1.0
 * @Author liukai
 */
@Data
public class WeatherLives {
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域编码
     */
    private String adcode;
    /**
     * 天气现象
     */
    private String weather;
    /**
     * 温度
     */
    private String temperature;
    /**
     * 风向
     */
    private String winddirection;
    /**
     * 风力
     */
    private String windpower;
    /**
     * 湿度
     */
    private String humidity;
    /**
     * 发布时间
     */
    private String reporttime;
}
