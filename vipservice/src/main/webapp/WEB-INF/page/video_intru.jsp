<%@ page import="com.operational.platform.common.util.Config" %>
<%@ page import="com.operational.platform.dbservice.model.VideoGood" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appPath = Config.get("app.name");
    VideoGood videoGood = (VideoGood) request.getAttribute("videoGood");
%>
<!DOCTYPE html>
<html itemscope="" itemtype="http://schema.org/WebPage">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=videoGood.getTitle()%>
    </title>
    <meta name="viewport"
          content="width=device-width, height=device-height, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <link href="<%=appPath%>/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=appPath%>/css/76923d703f3024fc.css">
</head>
<body>
<div class="page">
    <div class="card">
        <h1 class="title"><%=videoGood.getTitle()%>
        </h1>
        <section class="subject-info">
            <div class="right">
                <a><img src="<%=videoGood.getImage()%>" alt="<%=videoGood.getImage()%>" class="cover"></a>
            </div>
            <div class="left">
                <p class="rating"><%=videoGood.getScore()%></p>
                <p class="meta"><%=videoGood.getMeta()%></p>
            </div>
        </section>
        <div class="vendors-link-group">
            <div id="douban-vendors"></div>
        </div>
        <input id="user" type="hidden" value="">
        <section class="subject-intro">
            <h2><%=videoGood.getTitle()%>的内容简介</h2>
            <div class="bd" style="position: static;">
                <p data-clamp="3"><%=videoGood.getDescription()%></p>
            </div>
        </section>
        <div id="discussions-root">
            <div data-reactroot="" class="loading"></div>
        </div>
        <div class="center">
            <div id="dale_talion_subject_book_bottom" class="Advertisement" ad-status="loaded" data-sell-type="CPC"
                 data-type="GDTRender" data-version="3.0.10"></div>
        </div>
    </div>
</div>
</body>
</html>