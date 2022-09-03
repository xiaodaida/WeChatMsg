package org.example.api.requestresult.tianxing.constellation;


import lombok.Data;
import org.example.api.requestresult.tianxing.DefaultInput;

/**
 * @ClassName ConstellationInput
 * @Description TODO
 * @date 2022/8/24 22:03
 * @Version 1.0
 * @Author liukai
 */
@Data
public class ConstellationInput extends DefaultInput {

    /**
     * 星座中文或者英文名
     */
    private String astro;
    /**
     * 日期 YYYY-MM-DD
     */
    private String date;
}
