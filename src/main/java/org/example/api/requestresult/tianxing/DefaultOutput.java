package org.example.api.requestresult.tianxing;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @ClassName DefaultOutput
 * @Description TODO
 * @date 2022/8/24 22:00
 * @Version 1.0
 * @Author liukai
 */
@Data
public class DefaultOutput {
    private String code;
    private String msg;
    private JSONArray newslist;
}
