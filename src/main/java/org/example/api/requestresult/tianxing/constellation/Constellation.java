package org.example.api.requestresult.tianxing.constellation;

import lombok.Data;

/**
 * @ClassName Constellation
 * @Description 星座测算api
 * @date 2022/8/24 22:03
 * @Version 1.0
 * @Author liukai
 */
@Data
public class Constellation {

    /**
     * 指数名称
     */
    private String type;
    /**
     * 实际指数
     */
    private String content;
}
