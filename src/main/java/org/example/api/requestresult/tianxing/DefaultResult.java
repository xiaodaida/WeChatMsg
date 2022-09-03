package org.example.api.requestresult.tianxing;

import lombok.Data;

import java.util.List;

/**
 * @ClassName DefaultResult
 * @Description TODO
 * @date 2022/8/25 20:02
 * @Version 1.0
 * @Author liukai
 */
@Data
public class DefaultResult {

    private String code;
    private String msg;
    private List<Object> newslist;
}
