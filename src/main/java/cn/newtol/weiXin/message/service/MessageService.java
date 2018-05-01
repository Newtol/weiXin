package cn.newtol.weiXin.message.service;

import java.util.Map;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 16:11 2018/4/29
 */
public interface MessageService {
    public String repMessage(Map<String,String> message);
    public String textMessage(Map<String,String> message,String content);
}
