package cn.newtol.weiXin.message.service;

import java.util.Map;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 16:12 2018/4/29
 */
public class MessageServiceImp implements MessageService {

    @Override
    public String repMessage(Map<String, String> message) {
        String MsgType = message.get("MsgType");
        String content = null;
        String res = null;
        switch (MsgType){
            case "text":
                content =  "文字消息";
                break;
            case "image":
                content = "图片消息";
                break;
            case "voice":
                content = "语音消息";
                break;
            case "video":
                content = "视频消息";
                break;
            case "shortvideo":
                content = "小视频消息";
                break;
            case "location":
                content = "地理位置消息";
                break;
            case "link":
                content = "链接消息";
                break;
            default:
                content = "非普通消息类型";
        }
        res = textMessage(message,content);
        return res;

    }

    @Override
    public String textMessage(Map<String, String> message ,String content) {
        String xml = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[%s]]></MsgType>" +
                "<Content><![CDATA[%s]]></Content>" +
                "</xml>";
        String toUserName = message.get("FromUserName");
        String fromUser = message.get("ToUserName");
        String createTime = System.currentTimeMillis() / 1000 + "";
        String msgType = "text";
        String res = String.format(xml, toUserName, fromUser, createTime, msgType, content);
        return res;
    }
}
