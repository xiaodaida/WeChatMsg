package org.example.api;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import org.example.api.requestresult.gaode.WeatherInput;
import org.example.api.requestresult.gaode.WeatherOutput;
import org.example.api.requestresult.tianxing.DefaultOutput;
import org.example.api.requestresult.tianxing.constellation.ConstellationInput;
import org.example.api.requestresult.tianxing.constellationpair.ConstellationPairInput;
import org.example.enums.ApiRequestUrlEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OtherApiServiceImpl
 * @Description TODO
 * @date 2022/8/24 21:26
 * @Version 1.0
 * @Author liukai
 */
@Service
public class OtherApiServiceImpl implements OtherApiService{

    @Value("${GaoDe.key}")
    private String GaodeKey;

    @Value("${TianXing.key}")
    private String TianXingKey;

    @Override
    public WeatherOutput getWeather(WeatherInput weatherInput) {
        WeatherOutput weatherOutput = new WeatherOutput();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("key", GaodeKey);
        requestMap.put("city", weatherInput.getCity());
        requestMap.put("extensions", weatherInput.getExtensions());
        String result = HttpUtil.get(ApiRequestUrlEnum.GAODE_WEATHER_URL.getValue(), requestMap);
        weatherOutput = JSONObject.parseObject(result, WeatherOutput.class);
        return weatherOutput;
    }

    @Override
    public DefaultOutput getConstellation(ConstellationInput constellationInput) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("key", TianXingKey);
        requestMap.put("astro", constellationInput.getAstro());
        requestMap.put("date", constellationInput.getDate());
        String result = HttpUtil.get(ApiRequestUrlEnum.TIANXING_CONSTELLATION_URL.getValue(), requestMap);
        DefaultOutput defaultOutput = JSONObject.parseObject(result, DefaultOutput.class);
        return defaultOutput;
    }

    @Override
    public DefaultOutput getConstellationPair(ConstellationPairInput constellationPairInput) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("key", TianXingKey);
        requestMap.put("me", constellationPairInput.getMe());
        requestMap.put("he", constellationPairInput.getHe());
        String result = HttpUtil.get(ApiRequestUrlEnum.TIANXING_CONSTELLATION_PAIR_URL.getValue(), requestMap);
        DefaultOutput defaultOutput = JSONObject.parseObject(result, DefaultOutput.class);
        return defaultOutput;
    }
}
