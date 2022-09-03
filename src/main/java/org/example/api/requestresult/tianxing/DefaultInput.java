package org.example.api.requestresult.tianxing;

import lombok.Data;

/**
 * @ClassName DefaultInput
 * @Description TODO
 * @date 2022/8/24 22:04
 * @Version 1.0
 * @Author liukai
 */
@Data
public class DefaultInput {

    /**
     * key值
     */
    private String key;

    /**
     * 发送模板消息userId
     */
    private String userId;
}
