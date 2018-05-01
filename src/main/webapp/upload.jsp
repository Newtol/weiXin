<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/5/1
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>素材上传</title>
</head>
<body>
<form method="post" action="/weiXin/upload" enctype="application/x-www-form-urlencoded multipart/form-data">
素材的类型：
    <select class="type_select" name="type">
        <option value="image">图片</option>
        <option value="video">视频</option>
        <option value="voice">语音</option>
        <option value="thumb">略缩图</option>
    </select>
    </br>
    素材存储类型：
    <select class="time_select" name="time">
        <option value="long">永久</option>
        <option value="short">临时</option>
    </select></br>
    素材链接：
    <input type="text" name="url" placeholder="输入文件地址，本机文件直接填地址即可">
    <input type="submit" value="上传" />
</form>
</body>

</html>
