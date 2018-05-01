package cn.newtol.weiXin.menu;


import cn.newtol.weiXin.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class Button {
//    button	    是	             一级菜单数组，个数应为1~3个
//    sub_button	否	             二级菜单数组，个数应为1~5个
//    type	        是	             菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
//    name      	是	             菜单标题，不超过16个字节，子菜单不超过60个字节
//    key	click等点击类型必须	     菜单KEY值，用于消息接口推送，不超过128字节
//    url	view miniprogram类型必须  网页链接，用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。
//    media_id	media_id类型和view_limited类型必须	  调用新增永久素材接口返回的合法media_id
//    appid      miniprogram类型必须	 小程序的appid（仅认证公众号可配置）
//    pagepath   miniprogram类型必须	 小程序的页面路径

    public String name;

    public String type;

    public String key;

    public String url;

    public String mediaId;

    public String appId;

    public String pagepath;

    public List<Button> subButton;

    public int level;


    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        if (!StringUtil.isBlank(name)) jsonObject.put("name", name);
        if (!StringUtil.isBlank(type)) jsonObject.put("type", type);
        if (!StringUtil.isBlank(key)) jsonObject.put("key", key);
        if (!StringUtil.isBlank(url)) jsonObject.put("url", url);
        if (!StringUtil.isBlank(mediaId)) jsonObject.put("media_id", mediaId);
        if (!StringUtil.isBlank(appId)) jsonObject.put("appid", appId);
        if (!StringUtil.isBlank(pagepath)) jsonObject.put("pagepath", pagepath);
        if (subButton != null && subButton.size() > 0) jsonObject.put("sub_button", getSubButtonJSONArray());
        return jsonObject;
    }

    private JSONArray getSubButtonJSONArray() {
        JSONArray array = null;
        if (subButton != null && subButton.size() > 0) {
            array = new JSONArray();
            for (Button button : subButton) {
                array.put(button.toJson());
            }
        }
        return array;
    }

    public void addButton(Button button) {
        if (subButton == null) {
            subButton = new ArrayList<Button>();
        }
        subButton.add(button);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}