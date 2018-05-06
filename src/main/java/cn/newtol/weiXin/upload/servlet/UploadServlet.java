package cn.newtol.weiXin.upload.servlet;

import cn.newtol.weiXin.upload.service.UploadService;
import cn.newtol.weiXin.upload.service.UploadSeviceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static cn.newtol.weiXin.util.StringUtil.isBlank;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 15:59 2018/5/1
 */
public class UploadServlet extends HttpServlet{
    static UploadService  uploadService = new UploadSeviceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str =null;
        String type = req.getParameter("type");
        String time = req.getParameter("time");
        String fileUrl = null;
        String name = null;
        if(!isBlank(req.getParameter("url")) && !isBlank(req.getParameter("name"))){
            fileUrl = req.getParameter("url");
            name = req.getParameter("name");
        }else {
            return;
        }
        try {
             str = uploadService.Upload(fileUrl,type,time,name);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/JavaScript; charset=utf-8");
        resp.getWriter().println(str);
    }


}
