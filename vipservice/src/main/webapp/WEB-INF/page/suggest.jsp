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
    <form>
        <input id="type" name="type" type="hidden" value="<%=type %>"/>
        <input id="openid" name="openid" type="hidden" value="<%=openid %>"/>
        <input id="status" name="status" type="hidden" value="1"/>
        <textarea id="content" name="content"></textarea>
        <input type="button" value="提交" onclick="save()"/>
    </form>
    <div data-role="popup" id="message">
        <p>提交成功，感谢您的支持</p>
    </div>
</div>
</body>
<script>
    function save() {
        alert($("#content").val());
        $.ajax({
            url: '/suggest/save',
            type: 'post',
            data: {
                "type": $("#type").val(),
                "openid": $("#openid").val(),
                "status": $("#status").val(),
                "content": $("#content").val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    $("#message").popup('open');
                }
            }
        });
    }
</script>
</html>
