package org.example.api.requestresult.tianxing.constellationpair;

import lombok.Data;

/**
 * @ClassName ConstellationPair
 * @Description 星座匹配api
 * @date 2022/8/25 20:08
 * @Version 1.0
 * @Author liukai
 */
@Data
public class ConstellationPair {

    /**
     * 匹配星座
     */
    private String title;
    /**
     * 匹配指数
     */
    private String grade;
    /**
     * 匹配描述
     */
    private String content;
}
