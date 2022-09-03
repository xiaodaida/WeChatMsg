package org.example.wechat.templateservice;


import org.example.api.requestresult.gaode.WeatherInput;
import org.example.api.requestresult.tianxing.constellation.ConstellationInput;
import org.example.api.requestresult.tianxing.constellationpair.ConstellationPairInput;
import org.example.wechat.templatemsg.WeChatMsgResult;

/**
 * @ClassName TemplateService
 * @Description TODO
 * @date 2022/8/25 20:37
 * @Version 1.0
 * @Author liukai
 */
public interface TemplateService {

    WeChatMsgResult sendWeatherMsg(WeatherInput weatherInput);

    WeChatMsgResult sendConstellation(ConstellationInput constellationInput);

    WeChatMsgResult sendConstellationPair(ConstellationPairInput constellationPairInput);

    WeChatMsgResult sendWeatherAndCons(WeatherInput weatherInput, ConstellationInput constellationInput);

    WeChatMsgResult sendWeatherConsAndPair(WeatherInput weatherInput, ConstellationInput constellationInput, ConstellationPairInput constellationPairInput);
}
