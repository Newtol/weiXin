package cn.newtol.weiXin.getUserInfo.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 20:59 2018/4/10
 */
public interface WeiXinService {
    //插入用户信息
    public boolean insertUserInfo(Map<String, String> userInfo);
    //查询用户是否已经存在
    public Map<String,String> getUserInfo(String openId);
    //判断该用户是否存在session
    public String getOpenId(HttpSession session);

}
