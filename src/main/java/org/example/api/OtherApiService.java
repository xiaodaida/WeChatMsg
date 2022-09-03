package org.example.api;


import org.example.api.requestresult.gaode.WeatherInput;
import org.example.api.requestresult.gaode.WeatherOutput;
import org.example.api.requestresult.tianxing.DefaultOutput;
import org.example.api.requestresult.tianxing.constellation.ConstellationInput;
import org.example.api.requestresult.tianxing.constellationpair.ConstellationPairInput;

/**
 * @ClassName OtherApiService
 * @Description TODO
 * @date 2022/8/24 20:30
 * @Version 1.0
 * @Author liukai
 */
public interface OtherApiService {

    WeatherOutput getWeather(WeatherInput input);

    DefaultOutput getConstellation(ConstellationInput constellationInput);

    DefaultOutput getConstellationPair(ConstellationPairInput constellationPairInput);
}
