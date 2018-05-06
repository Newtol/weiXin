package cn.newtol.weiXin.upload.service;

import cn.newtol.weiXin.upload.dao.IUpload;
import cn.newtol.weiXin.util.AccessToken;
import cn.newtol.weiXin.util.CurlUtil;
import cn.newtol.weiXin.util.JsonUtil;
import cn.newtol.weiXin.util.SqlSessionFactoryUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import static cn.newtol.weiXin.upload.util.CurlUtil.connectHttpsByPost;
import static cn.newtol.weiXin.util.AccessToken.getAccessToken;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 16:44 2018/5/1
 */
public class UploadSeviceImp implements UploadService {
    @Override
    public String Upload(String fileUrl, String type, String time,String name) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String postUrl = null;
        String res = null;
        JSONObject jsonObject = new JSONObject();
        //获取access_token
        String access_token = getAccessToken();
        if(time.equals("long")){
            postUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + access_token + "&type=" + type;;
        }else{
            postUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+access_token+"&type="+type;
        }

        File file = new File(fileUrl);
        //上传素材
        Map<String,String>  map= new HashMap<>();
        String result = connectHttpsByPost(postUrl, file);
        map = JsonUtil.stringToCollect(result);
        if (map!= null || !map.isEmpty()) {
            if (map.get("media_id") != null) {
                map.put("file_type",type);
                map.put("time_type",time);
                map.put("media_name",name);
                String str = insert(map);
                jsonObject.put("message",str);
            }else {
                jsonObject.put("message","失败");
            }
        }else {
            jsonObject.put("message","失败");
        }
        res = jsonObject.toString();
        return res;
    }

    @Override
    public String insert(Map<String, String> map) {
        String str = null;
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        IUpload iuser = session.getMapper(IUpload.class);
        int i = iuser.insert(map);
        if(i!= 0){
            str = "插入成功";
        }else {
            str = "插入失败";
        }
        session.close();
        return str;
    }

    @Override
    public String delete(String mediaId) {
        String str = null;
        //请求的url
        String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token="+ AccessToken.getAccessToken();
        //请求数据构建
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id",mediaId);
        String  data = jsonObject.toString();
        //分析请求结果
        String result = CurlUtil.postData(url,data);
        Map resultMap = JsonUtil.stringToCollect(result);
        if(resultMap.get("errcode").equals("0")||resultMap.get("errmsg").equals("ok")){
            SqlSession session = SqlSessionFactoryUtil.getSqlSession();
            IUpload iUpload = session.getMapper(IUpload.class);
            int i = iUpload.delete(mediaId);
            if(i!=0){
                str = "删除成功";
            }else{
                str = "数据库删除失败";
            }
        }else{
             str = "微信服务器删除失败";
        }
        jsonObject.put("message",str);
        String resultMessage = jsonObject.toString();
        System.out.println(result);
        return resultMessage;
    }

    public static void main(String[] args) {
        UploadService uploadService = new UploadSeviceImp();
        String  str = uploadService.delete("tP0y34JmbzKUFw-m4Zy6U-2FkWBjmPZcjCy4d_yI3JU");
        System.out.println(str);
    }

}
