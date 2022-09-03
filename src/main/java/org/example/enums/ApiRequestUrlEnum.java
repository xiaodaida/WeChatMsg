package org.example.enums;


import org.example.enums.base.BaseStringEnum;

/**
 * @ClassName ApiRequestUrlEnum
 * @Description TODO
 * @date 2022/8/24 21:31
 * @Version 1.0
 * @Author liukai
 */
public enum ApiRequestUrlEnum implements BaseStringEnum {

    GAODE_WEATHER_URL("https://restapi.amap.com/v3/weather/weatherInfo"),

    TIANXING_CONSTELLATION_URL("http://api.tianapi.com/star/index"),

    TIANXING_CONSTELLATION_PAIR_URL("http://api.tianapi.com/xingzuo/index");

    private String url;

    ApiRequestUrlEnum(String url) {
        this.url = url;
    }

    @Override
    public String getValue() {
        return this.url;
    }
}
