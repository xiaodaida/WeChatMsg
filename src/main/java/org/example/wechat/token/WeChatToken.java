package org.example.wechat.token;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.example.redisutil.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WeChatToken
 * @Description TODO
 * @date 2022/8/29 22:36
 * @Version 1.0
 * @Author liukai
 */
@Component
public class WeChatToken {
    private static final Logger logger = LoggerFactory.getLogger(WeChatToken.class);

    @Value("${WeChat.appid}")
    private String APPID;
    @Value("${WeChat.secret}")
    private String SECRET;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取微信access_token
     */
    public String getAccessToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", APPID);
        map.put("secret", SECRET);
        String accessToken = StringUtils.SPACE;
        try {
            // 将access_token存到缓存里，单纯测试可以不用
            accessToken = (String) redisUtil.get("access_token");
        } catch (Exception e) {
            logger.error("获取微信token失败", e);
        }
        if (StringUtils.isBlank(accessToken)) {
            String s = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential", map);
            System.out.println(s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            accessToken = (String) jsonObject.get("access_token");
            redisUtil.set("access_token", accessToken, 60*60);
            logger.info("定时刷新微信token");
        }
        return accessToken;
    }
}
