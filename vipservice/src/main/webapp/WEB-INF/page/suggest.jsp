<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String type = (String) request.getAttribute("type");
    String openid = (String) request.getAttribute("openid");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/themes/default/easyui.css">
    <script type="text/javascript" src="/statics/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div>
    <form action="" method="post">
        <input name="type" type="hidden" value="<%=type %>"/>
        <input name="openid" type="hidden" value="<%=openid %>"/>
        <input name="status" type="hidden" value="1"/>
        <textarea name="content"></textarea>
        <input type="submit" value="提交"/>
    </form>
</div>
</body>
</html>
