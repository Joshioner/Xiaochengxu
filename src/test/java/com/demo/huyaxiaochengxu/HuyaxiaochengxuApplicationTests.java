package com.demo.huyaxiaochengxu;

import com.alibaba.fastjson.JSONObject;
import com.demo.huyaxiaochengxu.entity.AppInfo;
import com.demo.huyaxiaochengxu.util.HttpUtil;
import com.demo.huyaxiaochengxu.util.JwtUtil;
import com.demo.huyaxiaochengxu.util.ParamsUtil;
import com.demo.huyaxiaochengxu.util.WebSocketClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.java_websocket.enums.ReadyState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HuyaxiaochengxuApplicationTests {

    //根据房间号获取主播签约公会信息
    @Test
    public void contextLoads() {
        String url = "https://open-apiext.huya.com/channel/index";

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("do","getChannelInfoByRoom");
        paramsMap.put("roomId",852674);
        Map<String,Object> authMap = JwtUtil.getJwtParamsMap(paramsMap);
        url = url + ParamsUtil.MapToUrlString(authMap);
        String result = HttpUtil.doGet(url);
        JSONObject jsonObject= JSONObject.parseObject(result);
        if (jsonObject.getInteger("code") == 200){
            System.out.println(jsonObject.getString("message"));
            System.out.println(jsonObject.getString("data"));
        }

    }

    //开放API 获取礼物素材列表
    @Test
    public void getLiveGiftInfoListTest() {
        String url = "https://open-apiext.huya.com/proxy/index";

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("do","getLiveGiftInfoList");
        Map<String,Object> authMap = JwtUtil.getJwtParamsMap(paramsMap);
        url = url + ParamsUtil.MapToUrlString(authMap);
        String result = HttpUtil.doGet(url);
        JSONObject jsonObject= JSONObject.parseObject(result);
        if (jsonObject.getInteger("code") == 200){
            System.out.println(jsonObject.getString("message"));
            System.out.println(jsonObject.getString("data"));
        }
    }
    //	订阅消息/取消订阅消息(websocket协议)
    @Test
    public void subscribeTest(){
        try {
        String url = "ws://ws-apiext.huya.com/index.html";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("do","comm");
        map.put("roomId",520520);
        map = JwtUtil.getJwtParamsMap(map);
        url = url + ParamsUtil.MapToUrlString(map);
        WebSocketClient myClient = new WebSocketClient(URI.create(url));
        if (myClient != null) {
            myClient.connect();

            while (!myClient.getReadyState().equals(ReadyState.OPEN)) {
            }
//            while (true){
//                myClient.send("{\"command\":\"subscribeNotice\",\"data\":[\"getSendItemNotice\"],\"reqId\":\"123456789\"}");
//            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    @Test
    public void test(){
//        //密钥
        String key = AppInfo.getAPPSECRET();
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NjMwMTE0NzYsImV4cCI6MTU2MzAxMjA3NiwiYXBwSWQiOiJnYjgxYTUyMzM2MzBiMmU5In0.tjKIqbXDYSOkJBdELVpq8OTigeROfpDOmp6Ri9xJ5oc";
            //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        String appId = (String) claims.get("appId");
        System.out.println(appId);

    }
}
