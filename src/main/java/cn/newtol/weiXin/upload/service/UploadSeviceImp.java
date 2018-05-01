package cn.newtol.weiXin.upload.service;

import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static cn.newtol.weiXin.upload.util.CurlUtil.connectHttpsByPost;
import static cn.newtol.weiXin.util.AccessToken.getAccessToken;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 16:44 2018/5/1
 */
public class UploadSeviceImp implements UploadService {
    @Override
    public String Upload(String fileUrl, String type, String time) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String postUrl = null;
        String res = null;
        //获取access_token
        String access_token = getAccessToken();
        if(time.equals("long")){
            postUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + access_token + "&type=" + type;;
        }else{
            postUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+access_token+"&type="+type;
        }

        File file = new File(fileUrl);
        //上传素材
        String result = connectHttpsByPost(postUrl, file);
        JSONObject resultJSON = JSONObject.fromObject(result);
        if (resultJSON != null) {
            if (resultJSON.get("media_id") != null) {
                res = resultJSON.toString();
            } else {
                JSONObject jsonObject = null;
                jsonObject.put("message","失败");
                res = jsonObject.toString();
            }
        }
        return res;
    }
}
