package org.example.api.requestresult.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @ClassName WeatherOutput
 * @Description TODO
 * @date 2022/8/24 21:57
 * @Version 1.0
 * @Author liukai
 */
@Data
public class WeatherOutput {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private JSONArray lives;
    private JSONObject forecast;
}
