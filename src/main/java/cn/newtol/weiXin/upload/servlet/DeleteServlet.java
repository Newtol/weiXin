package cn.newtol.weiXin.upload.servlet;

import cn.newtol.weiXin.upload.service.UploadService;
import cn.newtol.weiXin.upload.service.UploadSeviceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: REN
 * @Description:
 * @Date: Created in 15:36 2018/5/6
 */
public class DeleteServlet extends HttpServlet {
    static UploadService uploadService = new UploadSeviceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mediaId = req.getParameter("mediaId");
        resp.setContentType("text/JavaScript; charset=utf-8");
        String str = uploadService.delete(mediaId);
        resp.setContentType("text/JavaScript; charset=utf-8");
        resp.getWriter().println(str);
    }
}
