package org.example.wechat.templateservice;


import org.apache.commons.lang3.StringUtils;
import org.example.api.OtherApiService;
import org.example.api.requestresult.gaode.WeatherInput;
import org.example.api.requestresult.gaode.WeatherLives;
import org.example.api.requestresult.gaode.WeatherOutput;
import org.example.api.requestresult.tianxing.DefaultOutput;
import org.example.api.requestresult.tianxing.constellation.Constellation;
import org.example.api.requestresult.tianxing.constellation.ConstellationInput;
import org.example.api.requestresult.tianxing.constellationpair.ConstellationPair;
import org.example.api.requestresult.tianxing.constellationpair.ConstellationPairInput;
import org.example.enums.TemplateMsgEnum;
import org.example.timeutil.TimeUtils;
import org.example.wechat.templatemsg.WeChatMessage;
import org.example.wechat.templatemsg.WeChatMsgResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TemplateServiceImpl
 * @Description TODO
 * @date 2022/8/25 20:48
 * @Version 1.0
 * @Author liukai
 */
@Service
public class TemplateServiceImpl implements TemplateService{
    private static final Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private static Map<String, String> CONSTELL_FILELD_MAP = new HashMap<>();

    @Autowired
    private OtherApiService otherApiService;

    @Autowired
    private WeChatMessage weChatMessage;

    @Override
    public WeChatMsgResult sendWeatherMsg(WeatherInput weatherInput) {
        Map<String, Object> valueMap = getWeatherMsg(weatherInput);
        WeChatMsgResult weChatMsgResult = new WeChatMsgResult();
        weChatMsgResult = weChatMessage.sendPublicMessage(TemplateMsgEnum.WEATHER_MSG.getValue(), weatherInput.getUserId(), valueMap);
        return weChatMsgResult;
    }

    @Override
    public WeChatMsgResult sendConstellation(ConstellationInput constellationInput) {
        Map<String, Object> valueMap = getConstellation(constellationInput);
        WeChatMsgResult weChatMsgResult = new WeChatMsgResult();
        weChatMsgResult = weChatMessage.sendPublicMessage(TemplateMsgEnum.WEATHER_MSG.getValue(), constellationInput.getUserId(), valueMap);
        return weChatMsgResult;
    }

    @Override
    public WeChatMsgResult sendConstellationPair(ConstellationPairInput constellationPairInput) {
        Map<String, Object> valueMap = getConstellationPair(constellationPairInput);
        WeChatMsgResult weChatMsgResult = new WeChatMsgResult();
        weChatMsgResult = weChatMessage.sendPublicMessage(TemplateMsgEnum.WEATHER_MSG.getValue(), constellationPairInput.getUserId(), valueMap);
        return weChatMsgResult;
    }

    @Override
    public WeChatMsgResult sendWeatherAndCons(WeatherInput weatherInput, ConstellationInput constellationInput) {
        Map<String, Object> valueMap = getWeatherMsg(weatherInput);
        valueMap.putAll(getConstellation(constellationInput));
        WeChatMsgResult weChatMsgResult = new WeChatMsgResult();
        weChatMsgResult = weChatMessage.sendPublicMessage(TemplateMsgEnum.ONE_DAY_GOOD_MOOD.getValue(), weatherInput.getUserId(), valueMap);
        return weChatMsgResult;
    }

    @Override
    public WeChatMsgResult sendWeatherConsAndPair(WeatherInput weatherInput, ConstellationInput constellationInput, ConstellationPairInput constellationPairInput) {
        Map<String, Object> valueMap = getWeatherMsg(weatherInput);
        valueMap.putAll(getConstellation(constellationInput));
        valueMap.putAll(getConstellationPair(constellationPairInput));
        WeChatMsgResult weChatMsgResult = new WeChatMsgResult();
        weChatMsgResult = weChatMessage.sendPublicMessage(TemplateMsgEnum.WEATHER_MSG.getValue(), weatherInput.getUserId(), valueMap);
        return weChatMsgResult;
    }

    /**
     * 组装天气value
     * @param weatherInput
     * @return
     */
    public Map<String, Object> getWeatherMsg(WeatherInput weatherInput) {
        Map<String, Object> valueMap = getDateAndWeek();
        try {
            WeatherOutput weatherOutput = new WeatherOutput();
            weatherOutput = otherApiService.getWeather(weatherInput);
            List<WeatherLives> weatherLives = weatherOutput.getLives().toJavaList(WeatherLives.class);
            if (weatherLives.size() > 0) {
                WeatherLives weatherLive = weatherLives.get(0);
                Map<Object, Object> map = new HashMap<>();
                map.put("color", "#009100");
                map.put("value", weatherLive.getCity());
                valueMap.put("city", map);
                Map<Object, Object> map1 = new HashMap<>();
                map1.put("color", "#009100");
                map1.put("value", weatherLive.getWeather());
                valueMap.put("weather", map1);
                Map<Object, Object> map2 = new HashMap<>();
                map2.put("color", "#009100");
                map2.put("value", weatherLive.getTemperature() + "℃");
                valueMap.put("temperature", map2);
                Map<Object, Object> map3 = new HashMap<>();
                map3.put("color", "#009100");
                map3.put("value", weatherLive.getWinddirection() + "风," + weatherLive.getWindpower() + "级");
                valueMap.put("wind", map3);
            }
        } catch (Exception e) {
            logger.error("天气数据获取处理失败", e);
        }
        return valueMap;

    }


    /**
     * 组装星座运势value
     * @param constellationInput
     * @return
     */
    public Map<String, Object> getConstellation(ConstellationInput constellationInput) {
        Map<String, Object> valueMap = getDateAndWeek();
        try {
            DefaultOutput defaultOutput = new DefaultOutput();
            Map<Object, Object> conMap = new HashMap<>();
            conMap.put("color", "#009100");
            conMap.put("value", constellationInput.getAstro());
            valueMap.put("conValue", conMap);
            defaultOutput = otherApiService.getConstellation(constellationInput);
            List<Constellation> constellations = defaultOutput.getNewslist().toJavaList(Constellation.class);
            constellations.forEach(constellation -> {
                Map<Object, Object> map = new HashMap<>();
                map.put("color", "#009100");
                map.put("value", constellation.getContent());
                valueMap.put(CONSTELL_FILELD_MAP.getOrDefault(constellation.getType(), StringUtils.SPACE), map);
            });
        } catch (Exception e) {
            logger.error("星座运势解析失败", e);
        }
        return valueMap;
    }

    public Map<String, Object> getConstellationPair(ConstellationPairInput constellationPairInput) {
        Map<String, Object> valueMap = getDateAndWeek();
        try {
            DefaultOutput defaultOutput = new DefaultOutput();
            defaultOutput = otherApiService.getConstellationPair(constellationPairInput);
            List<ConstellationPair> constellationPairs = defaultOutput.getNewslist().toJavaList(ConstellationPair.class);
            if (constellationPairs.size() > 0) {
                ConstellationPair constellationPair = constellationPairs.get(0);
                Map<Object, Object> titleMap = new HashMap<>();
                titleMap.put("color", "#009100");
                titleMap.put("value", constellationPair.getTitle());
                valueMap.put("conPairTitle", titleMap);
                Map<Object, Object> gradeMap = new HashMap<>();
                gradeMap.put("color", "#009100");
                gradeMap.put("value", constellationPair.getGrade());
                valueMap.put("conPairTitle", gradeMap);
                Map<Object, Object> contentMap = new HashMap<>();
                contentMap.put("color", "#009100");
                contentMap.put("value", constellationPair.getContent());
                valueMap.put("conPairContent", contentMap);
            }
        } catch (Exception e) {
            logger.error("星座配对解析失败", e);
        }
        return valueMap;
    }


    public Map<String, Object> getDateAndWeek() {
        Map<String, Object> valueMap = new HashMap<>();
        Map<Object, Object> dateMap = new HashMap<>();
        dateMap.put("color", "#009100");
        dateMap.put("value", TimeUtils.getSysDateAndWeek());
        valueMap.put("date", dateMap);
        return  valueMap;
    }

    static {
        CONSTELL_FILELD_MAP.put("综合指数", "conAllValue");
        CONSTELL_FILELD_MAP.put("爱情指数", "conLoveValue");
        CONSTELL_FILELD_MAP.put("工作指数", "conWorkValue");
        CONSTELL_FILELD_MAP.put("财运指数", "conMoneyValue");
        CONSTELL_FILELD_MAP.put("健康指数", "conHealthValue");
        CONSTELL_FILELD_MAP.put("幸运颜色", "conColorValue");
        CONSTELL_FILELD_MAP.put("幸运数字", "conNumberValue");
        CONSTELL_FILELD_MAP.put("速配Ｑ友", "conFriendValue");
        CONSTELL_FILELD_MAP.put("今日概述", "conDescValue");
    }
}
