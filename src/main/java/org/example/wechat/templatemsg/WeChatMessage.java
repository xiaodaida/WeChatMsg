package org.example.wechat.templatemsg;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.example.redisutil.RedisUtil;
import org.example.wechat.token.WeChatToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName WeCharMessage
 * @Description TODO
 * @date 2022/8/23 19:48
 * @Version 1.0
 * @Author liukai
 */
@Component
public class WeChatMessage {
    private static final Logger logger = LoggerFactory.getLogger(WeChatMessage.class);

    public static final String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    @Value("${WeChat.appid}")
    private String appid;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WeChatToken weChatToken;
    /**
     * 微信模板消息，通用
     */
    public WeChatMsgResult sendPublicMessage(String templateId, String toUserId, Map<String, Object> valueMap) {
        WeChatMsgResult weChatMsgResult = new WeChatMsgResult();
        // 获取access_token
        String accessToken = weChatToken.getAccessToken();
        // 设置模板消息基本参数
        Map<String, Object> map = new HashMap<>();
        map.put("touser", toUserId);
        map.put("template_id", templateId);
        map.put("appid", appid);

        try {
            weChatMsgResult = sendMessage(TEMPLATE_URL + accessToken, map, valueMap);
        } catch (IllegalAccessException | InstantiationException e) {
            logger.error("发送模板消息异常:{}", e.getMessage());
        }
        return weChatMsgResult;
    }
    /**
     * 发送消息
     * @param url 微信模板消息url
     */
    private WeChatMsgResult sendMessage(String url, Map<String, Object> map, Map<String, Object> valueMap) throws IllegalAccessException, InstantiationException {
        map.put("data", valueMap);
        String msg = JSON.toJSONString(map);
        String post = HttpUtil.post(url, msg);
        logger.info("发送模板消息{}", msg);
        WeChatMsgResult weChatMsgResult = JSONObject.parseObject(post, WeChatMsgResult.class);
        logger.info("收到应答{}", weChatMsgResult.toString());
        return weChatMsgResult;
    }

}
