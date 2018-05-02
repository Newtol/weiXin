package cn.newtol.weiXin.menu;
import cn.newtol.weiXin.util.AccessToken;
import cn.newtol.weiXin.util.CurlUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Menu {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Menu menu = new Menu();

        Button clickButton = new Button();
        clickButton.setType("click");
        clickButton.setName("click事件");
        clickButton.setKey("key1");

        Button viewButton = new Button();
        viewButton.setType("view");
        viewButton.setName("百度");
        viewButton.setUrl("https://www.baidu.com");
        viewButton.setKey("key2");

        Button scancodePushButton = new Button();
        scancodePushButton.setType("scancode_push");
        scancodePushButton.setName("scancode_push事件");
        scancodePushButton.setKey("key3");

        Button scancodeWaitmsgButton = new Button();
        scancodeWaitmsgButton.setType("scancode_waitmsg");
        scancodeWaitmsgButton.setName("scancode_waitmsg事件");
        scancodeWaitmsgButton.setKey("key4");

        Button picSysphotoButton = new Button();
        picSysphotoButton.setType("pic_sysphoto");
        picSysphotoButton.setName("pic_sysphoto事件");
        picSysphotoButton.setKey("key5");

        Button picPhotoOrAlbumButton = new Button();
        picPhotoOrAlbumButton.setType("pic_photo_or_album");
        picPhotoOrAlbumButton.setName("pic_photo_or_album事件");
        picPhotoOrAlbumButton.setKey("key6");

        Button picWeixinButton = new Button();
        picWeixinButton.setType("pic_weixin");
        picWeixinButton.setName("pic_weixin事件");
        picWeixinButton.setKey("key7");

        Button locationSelect = new Button();
        locationSelect.setType("location_select");
        locationSelect.setName("location_select事件");
        locationSelect.setKey("key8");

        Button button1 = new Button();
        button1.setName("菜单1");
        button1.addButton(viewButton);
        button1.addButton(scancodePushButton);
        button1.addButton(scancodeWaitmsgButton);

        Button button2 = new Button();
        button2.setName("菜单2");
        button2.addButton(picSysphotoButton);
        button2.addButton(picWeixinButton);
        button2.addButton(picPhotoOrAlbumButton);

        Button button3 = new Button();
        button3.setName("菜单3");
        button3.addButton(clickButton);
        button3.addButton(locationSelect);

        menu.addButton(button1);
        menu.addButton(button2);
        menu.addButton(button3);
        String result = updateMenu(menu);
        System.out.println(result);
    }


    public static String updateMenu(Menu menu) {
        String accessToken = AccessToken.getAccessToken();
//        System.out.println(accessToken);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        String menuData = menu.toJson();
        return CurlUtil.postData(url, menuData);
    }

    private List<Button> button;

    public String toJson() {
        JSONArray buttonData = new JSONArray();
        JSONObject menuData = new JSONObject();
        for (int i = 0; i < button.size(); i++) {
            Button b = button.get(i);
            buttonData.add(i, b.toJson());
        }
        menuData.put("button", buttonData);
        return menuData.toString();
    }

    public void addButton(Button button) {
        if (this.button == null) {
            this.button = new ArrayList<Button>();
        }
        this.button.add(button);
    }
}

