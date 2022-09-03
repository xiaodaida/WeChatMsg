package org.example.api.requestresult.tianxing.constellationpair;


import lombok.Data;
import org.example.api.requestresult.tianxing.DefaultInput;

/**
 * @ClassName ConstellationPairInput
 * @Description TODO
 * @date 2022/8/25 20:06
 * @Version 1.0
 * @Author liukai
 */
@Data
public class ConstellationPairInput extends DefaultInput {

    /**
     * 我的星座
     */
    private String me;
    /**
     * 他的星座
     */
    private String he;
    /**
     * 是否与其他星座匹配
     */
    private Integer all;
}
