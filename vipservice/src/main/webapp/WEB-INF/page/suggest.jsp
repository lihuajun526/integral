<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String type = (String) request.getAttribute("type");
    String openid = (String) request.getAttribute("openid");
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/statics/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="/statics/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script src="/statics/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>
<div>
    <form action="" method="post">
        <input name="type" type="hidden" value="<%=type %>"/>
        <input name="openid" type="hidden" value="<%=openid %>"/>
        <input name="status" type="hidden" value="1"/>
        <textarea name="content"></textarea>
        <input type="button" value="提交" onclick="test()"/>
    </form>
    <div data-role="popup" id="message">
        <p>这是一个简单的弹窗</p>
    </div>
</div>
</body>
<script>
    function test(){
        $("#message").popup('open');
    }
</script>
</html>
