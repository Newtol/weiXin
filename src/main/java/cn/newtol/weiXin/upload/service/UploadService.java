package cn.newtol.weiXin.upload.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 16:32 2018/5/1
 */
public interface UploadService {
    public String Upload(String url,String type,String time) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException;
}
