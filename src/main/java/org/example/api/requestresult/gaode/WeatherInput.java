package org.example.api.requestresult.gaode;


import lombok.Data;
import org.example.api.requestresult.tianxing.DefaultInput;

/**
 * @ClassName WeatherInput
 * @Description TODO
 * @date 2022/8/24 21:56
 * @Version 1.0
 * @Author liukai
 */
@Data
public class WeatherInput extends DefaultInput {
    /**
     * 城市编码
     */
    private String city;
    /**
     * 气象类型
     * 可选值：base/all
     * base:返回实况天气
     * all:返回预报天气
     */
    private String extensions;
    /**
     * 返回类型
     */
    private String output;
}
