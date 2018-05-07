package cn.newtol.weiXin.getUserInfo.dao;

import java.util.Map;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 21:11 2018/4/10
 */
public interface IUser {
    //插入用户信息
    public int insertUserInfo(Map<String, String> map);
    //判断用户是否存在
    public Map<String,String> getUserInfo(String openId);
}
