package org.example.testController;

import org.example.api.requestresult.gaode.WeatherInput;
import org.example.api.requestresult.tianxing.constellation.ConstellationInput;
import org.example.wechat.templateservice.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @date 2022/9/3 16:18
 * @Version 1.0
 * @Author liukai
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private TemplateService templateService;

    @GetMapping(value = "getTest")
    public void sendTest() {
        WeatherInput weatherInput = new WeatherInput();
        weatherInput.setUserId("o4Jfp57-OeoVX7Jh47xlS1iL6mLI");
        weatherInput.setCity("330108");
        ConstellationInput constellationInput = new ConstellationInput();
        constellationInput.setAstro("天秤座");
        templateService.sendWeatherAndCons(weatherInput, constellationInput);
    }
}
