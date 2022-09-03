package org.example.wechat.templatemsg;

import lombok.Data;

/**
 * @ClassName WeChatMsgResult
 * @Description TODO
 * @date 2022/8/25 20:30
 * @Version 1.0
 * @Author liukai
 */
@Data
public class WeChatMsgResult {

    private Integer errcode;

    private String errmsg;

    private String msgid;
}
