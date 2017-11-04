<%@ page import="com.operational.platform.common.util.Config" %>
<%@ page import="java.util.List" %>
<%@ page import="com.operational.platform.dbservice.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appName = "影咖365";
    String appPath = Config.get("app.name");
    String rootPath = "https://m.douban.com";
    VideoGood videoGood = (VideoGood) request.getAttribute("videoGood");
    List<DbVideoPerson> dbVideoPersons = videoGood.getVideoPersons();
    List<DbVideoTag> dbVideoTags = videoGood.getVideoTags();
    List<DbVideoImage> dbVideoImages = videoGood.getVideoImages();
    List<DbShortComment> dbShortComments = videoGood.getShortComments();
    List<DbVideoRelation> dbVideoRelations = videoGood.getVideoRelations();
%>
<html itemscope="" itemtype="http://schema.org/WebPage">
<head>
    <meta charset="UTF-8">
    <title><%=videoGood.getTitle()%> - 电影 - <%=appName%>
    </title>
    <meta name="viewport"
          content="width=device-width, height=device-height, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <link href="<%=appPath%>/css/base.css" rel="stylesheet">
    <meta property="og:image:width" content="300">
    <meta property="og:image:height" content="300">
    <meta property="og:type" content="article">
    <!-- Wechat meta -->
    <meta property="weixin:timeline_title" content="<%=videoGood.getTitle()%> - 电影 - <%=appName%>">
    <meta property="weixin:chat_title" content="<%=videoGood.getTitle()%> - 电影 - <%=appName%>">
    <meta property="weixin:description"
          content="<%=videoGood.getTitle()%>评分：7.2 简介：<%=videoGood.getDescription().length()>190?videoGood.getDescription().substring(0,190)+"...":videoGood.getDescription()%>">
    <meta property="weixin:image" content="<%=videoGood.getWimage()%>">
    <link rel="stylesheet" href="https://img3.doubanio.com/misc/mixed_static/413d55b342dcbe35.css">
    <link rel="icon" type="image/png" sizes="16x16"
          href="http://www.yka365.com/upload/supervip/logo54.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="http://www.yka365.com/upload/supervip/logo54.png">
    <link rel="icon" type="image/png" sizes="48x48"
          href="http://www.yka365.com/upload/supervip/logo54.png">
</head>
<body>
<div class="page">
    <%--<section class="promo_top_banner" style="display: block !important">
        <div class="banner_bg">
            <img src="https://img3.doubanio.com/f/talion/fbcb08987a36258354c6037211d94286bef9716c/pics/card/promotion_bg.jpg"
                 class="img">
        </div>
        <div class="banner_wrapper">
            <div class="banner_inner">
                <div class="promo_title">
                    <span class="promo_title_text">用 App 打开</span><br>
                    <span class="promo_title_text">查看影人相册</span>
                </div>
                <div class="button_wrapper">
                    <a href="https://www.douban.com/doubanapp/app?model=B&amp;copy=1&amp;page=movie&amp;channel=card_movie&amp;direct_dl=1"
                       rel="nofollow" onclick="ga('send', 'event', 'android', 'click', 'subject banner download')"
                       class="promo_button download_app">极速下载</a>
                    <a href="https://www.douban.com/doubanapp/card/get_app?from=mdouban&amp;client_uri=douban%3A%2F%2Fdouban.com%2Fmovie%2F1307463&amp;model=B&amp;copy=1&amp;page=movie&amp;channel=card_movie"
                       rel="nofollow" onclick="ga('send', 'event', 'android', 'click', 'card more link')"
                       class="promo_button open_app">打开</a>
                </div>
            </div>
        </div>
    </section>--%>
    <div class="card">
        <h1 class="title"><%=videoGood.getTitle()%>
        </h1>
        <section class="subject-info">
            <div class="right">
                <a><img src="<%=videoGood.getImage()%>" class="cover"></a>
            </div>
            <div class="left">
                <p class="rating"><%=videoGood.getScore()%>
                </p>

                <p class="meta"><%=videoGood.getMeta()%>
                </p>
            </div>
        </section>
        <section class="subject-intro">
            <h2><%=videoGood.getTitle()%>的剧情简介</h2>

            <div class="bd" style="position: static;">
                <p data-clamp="3"><%=videoGood.getDescription()%>
                </p>
            </div>
        </section>
        <%
            if (dbVideoPersons.size() > 0) {
        %>
        <section class="" id="celebrities">
            <header>
                <h2>影人</h2>
            </header>
            <div class="section-content">
                <ul class="row items">
                    <%
                        for (DbVideoPerson dbVideoPerson : dbVideoPersons) {
                    %>
                    <li class="item item__celebrity">
                        <a href="<%=dbVideoPerson.getLink()%>">
                            <div class="item-poster"
                                 style="background-image: url(<%=dbVideoPerson.getPhoto()%>)">
                            </div>
                            <span class="item-title name"><%=dbVideoPerson.getName()%></span>
                            <span class="item-title role"><%=dbVideoPerson.getPosition()%></span>
                        </a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </section>
        <%
            }
        %>
        <%
            if (dbVideoTags.size() > 0) {
        %>
        <section class="tags">
            <ul>
                <%
                    for (DbVideoTag dbVideoTag : dbVideoTags) {
                %>
                <li>
                    <a href="<%=rootPath+dbVideoTag.getLink()%>"><%=dbVideoTag.getTitle()%>
                    </a>
                </li>
                <%
                    }
                %>
            </ul>
        </section>
        <%
            }
        %>
        <%
            if (dbVideoImages.size() > 0) {
        %>
        <section class="subject-pics">
            <h2><%=videoGood.getTitle()%>的预告片和图片</h2>

            <div class="bd photo-list">
                <ul class="wx-preview">
                    <%
                        for (DbVideoImage videoImage : dbVideoImages) {
                    %>
                    <li class="pic">
                        <a href="<%=rootPath+videoImage.getLink()%>">
                            <img src="<%=videoImage.getImage()%>">
                        </a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </section>
        <%

            }
        %>
        <%
            if (dbShortComments.size() > 0) {
        %>
        <section class="subject-comments">
            <h2><%=videoGood.getTitle()%>的短评</h2>

            <div class="bd">
                <ul class="list comment-list">
                    <%
                        for (DbShortComment shortComment : dbShortComments) {
                    %>
                    <li>
                        <div class="desc"><a><img src="<%=shortComment.getPhoto()%>"></a>

                            <div class="user-info">
                                <strong><%=shortComment.getName()%>
                                </strong>
                                <span class="rating-stars" data-rating="4">
                                    <%
                                        for (int i = 1; i <= 5; i++) {
                                            if (i <= shortComment.getStar()) {
                                    %><span class="rating-star rating-star-medium-full"></span><%
                                } else {
                                %><span class="rating-star rating-star-medium-gray"></span><%
                                        }
                                    }
                                %>
                                </span>

                                <div class="date"><%=shortComment.getDateTime()%>
                                </div>
                            </div>
                        </div>
                        <p><%=shortComment.getContent()%>
                        </p>

                        <div class="btn-info">
                            <div class="ic-btn ic-btn-like left ">
                                <span class="text"><%=shortComment.getPraise()%></span>
                            </div>
                            <div class="ic-btn ic-btn-more right"></div>
                        </div>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </section>
        <%
            }
        %>
        <%
            if (dbVideoRelations.size() > 0) {
        %>
        <section class="subject-rec">
            <h2>喜欢<%=videoGood.getTitle()%>的人也喜欢</h2>

            <div class="bd">
                <ul>
                    <%
                        for (DbVideoRelation videoRelation : dbVideoRelations) {
                    %>
                    <li>
                        <a href="<%=rootPath+videoRelation.getLink()%>">
                            <div class="wp">
                                <img alt="<%=videoRelation.getTitle()%>" data-type="cover"
                                     src="<%=videoRelation.getImage()%>">

                                <h3><%=videoRelation.getTitle()%>
                                </h3>
                            </div>
                        </a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </section>
        <%
            }
        %>
    </div>
    <div class="download-app">
        <div class="info">
            <img src="http://www.yka365.com/upload/supervip/logo.png" width="48">

            <div class="info-content">
                <strong>影咖365</strong>

                <div>免费观影，就是这么酷</div>
            </div>
        </div>
        <a href="http://www.yka365.com/appLoad/index.html">免费下载APP</a>
    </div>
    <div class="bottom_ad_download show">
        <div id="dale_tailon_movie_bottom_floating_inner" class="Advertisement" ad-status="loaded"></div>
    </div>
</div>
</body>
</html>