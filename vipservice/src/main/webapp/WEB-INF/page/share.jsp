<%@ page import="com.operational.platform.common.util.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appPath = Config.get("app.name");
    Object oTicket = request.getAttribute("ticket");
    Object oUrl = request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=0" />
    <meta charset="UTF-8">
    <title>关注影咖 - 扫一扫二维码</title>
    <link rel="stylesheet" href="<%=appPath%>/css/qrcode.css" />
    <link rel="stylesheet" href="<%=appPath%>/css/animate.css" />
</head>
<body>
<div class="main">
    <div class="section1 animated slideInDown"><img src="<%=appPath%>/images/img1.png"></div>
    <div class="section2 animated jackInTheBox"><img src="<%=appPath%>/images/img2.png"></div>
    <div class="section3 animated zoomInDown">
        <%
            if(oTicket==null){
        %><!--<img src="<%=oUrl%>">--><img src="<%=appPath%>/images/img3.png"/><%
    }else{
    %><img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=<%=oTicket%>"/><%
        }
    %>
    </div>
    <div class="section4 animated rotateIn"><img src="<%=appPath%>/images/img4.png"></div>
    <div class="section5 animated fadeInUp"><img src="<%=appPath%>/images/img5.png"></div>
    <div class="section6 animated fadeInUp"></div>
    <div class="section7 animated fadeInLeft"></div>
</div>
</body>
</html>
