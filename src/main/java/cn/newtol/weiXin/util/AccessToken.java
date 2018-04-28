package cn.newtol.weiXin.util;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import java.sql.*;

public class AccessToken {

    public static   String getAccessToken() {
        String accessToken = null;
        Jedis jedis = new Jedis("localhost");
        try {
            accessToken = getAccessTokenFromDatabase();
            if (accessToken == null) {
                synchronized (AccessToken.class) {
                    if (getAccessTokenFromDatabase() == null) {
                        accessToken = curlForAccessToken();
                        jedis.set(Const.AccessToken,accessToken);
                        jedis.expire(Const.AccessToken,7200);

                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jedis.close();
        return accessToken;
    }

    private static String getAccessTokenFromDatabase() throws SQLException, ClassNotFoundException {
        //access_token timestamp过期时间
        Jedis jedis = new Jedis();
        String accessToken = jedis.get(Const.AccessToken);;
        jedis.close();
        return accessToken;
    }

    private static String curlForAccessToken() {
        String appId = Const.AppId;
        String appSecret = Const.AppSecret;
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
        String result = CurlUtil.getContent(url, null, "GET");
        JSONObject data = JSONObject.parseObject(result);
        String accessToken = (String) data.get("access_token");
        return accessToken;
    }
    public static void main(String[] args) {
        String access_token = getAccessToken();
        System.out.println(access_token);
        Jedis jedis = new Jedis();
        String jedisAccessToken = jedis.get(Const.AccessToken);
        System.out.println("Redis:"+jedisAccessToken);
    }
}
