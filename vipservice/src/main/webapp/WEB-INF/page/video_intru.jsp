<%@ page import="com.operational.platform.common.util.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appPath = Config.get("app.name");
    Object oTicket = request.getAttribute("ticket");
%>
<!DOCTYPE html>
<html itemscope="" itemtype="http://schema.org/WebPage">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, height=device-height, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <link rel="canonical" href="https://m.douban.com/book/subject/1334151/">
    <link href="./十月的天空 - 图书 - 豆瓣_files/base.css" rel="stylesheet">
    <meta itemprop="description" content="十月的天空豆瓣评分：8.6 简介：
十月的天空
一九五七年十月五日蘇聯發射人類第一枚人造衛星「同路一號」，當時美國西維吉尼亞洲的煤林鎮是一個逐漸凋零、死寂的小鎮。面對茫茫未知的前途，荷默再小小的心靈裡孵著一個夢：送火箭上太空。
擔任煤...">
    <meta itemprop="image"
          content="https://qnmob2.doubanio.com/lpic/s3846387.jpg?imageView2/1/q/60/w/300/h/300/format/jpg">
    <meta itemprop="reviewCount" content="40">
    <meta itemprop="ratingValue" content="8.6">
    <!-- Twitter meta -->
    <meta name="twitter:card" content="summary">
    <!-- Open Graph meta -->
    <meta property="og:title" content="十月的天空 - 图书">
    <meta property="og:description" content="十月的天空豆瓣评分：8.6 简介：
十月的天空
一九五七年十月五日蘇聯發射人類第一枚人造衛星「同路一號」，當時美國西維吉尼亞洲的煤林鎮是一個逐漸凋零、死寂的小鎮。面對茫茫未知的前途，荷默再小小的心靈裡孵著一個夢：送火箭上太空。
擔任煤...">
    <meta property="og:site_name" content="豆瓣(手机版)">
    <meta property="og:url" content="https://m.douban.com/book/subject/1334151/">
    <meta property="og:image"
          content="https://qnmob2.doubanio.com/lpic/s3846387.jpg?imageView2/1/q/60/w/300/h/300/format/jpg">
    <meta property="og:image:type" content="image/png">
    <meta property="og:image:width" content="300">
    <meta property="og:image:height" content="300">
    <meta property="og:type" content="article">
    <!-- Wechat meta -->
    <meta property="weixin:timeline_title" content="十月的天空 - 图书 - 豆瓣">
    <meta property="weixin:chat_title" content="十月的天空 - 图书 - 豆瓣">
    <meta property="weixin:description" content="十月的天空豆瓣评分：8.6 简介：
十月的天空
一九五七年十月五日蘇聯發射人類第一枚人造衛星「同路一號」，當時美國西維吉尼亞洲的煤林鎮是一個逐漸凋零、死寂的小鎮。面對茫茫未知的前途，荷默再小小的心靈裡孵著一個夢：送火箭上太空。
擔任煤...">
    <meta property="weixin:image"
          content="https://qnmob2.doubanio.com/lpic/s3846387.jpg?imageView2/1/q/60/w/300/h/300/format/jpg">


    <link rel="stylesheet" href="./十月的天空 - 图书 - 豆瓣_files/76923d703f3024fc.css">
    <link rel="icon" type="image/png" sizes="16x16"
          href="https://img3.doubanio.com/f/talion/c970bb0d720963a7392f7dd6c77068bb9925caaf/pics/icon/dou16.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="https://img3.doubanio.com/f/talion/2f3c0bc0f35b031d4535fd993ae3936f4e40e6c8/pics/icon/dou32.png">
    <link rel="icon" type="image/png" sizes="48x48"
          href="https://img3.doubanio.com/f/talion/10a4a913a5715f628e4b598f7f9f2c18621bdcb3/pics/icon/dou48.png">
    <!-- iOS touch icon -->
    <link rel="apple-touch-icon-precomposed"
          href="https://img3.doubanio.com/f/talion/997f2018d82979da970030a5eb84c77f0123ae5f/pics/icon/m_logo_76.png">
    <link rel="apple-touch-icon-precomposed" sizes="76x76"
          href="https://img3.doubanio.com/f/talion/997f2018d82979da970030a5eb84c77f0123ae5f/pics/icon/m_logo_76.png">
    <link rel="apple-touch-icon-precomposed" sizes="120x120"
          href="https://img3.doubanio.com/f/talion/18932a3e71a60ed7150ca2ca7ebf21ddadd7092e/pics/icon/m_logo_120.png">
    <link rel="apple-touch-icon-precomposed" sizes="152x152"
          href="https://img3.doubanio.com/f/talion/b99497ff8538c54b9ba6f40867da932396ab2562/pics/icon/m_logo_152.png">
    <link rel="apple-touch-icon-precomposed" sizes="167x167"
          href="https://img3.doubanio.com/f/talion/0c233ada957a95e632f81607e30230d16e8293e8/pics/icon/m_logo_167.png">
    <link rel="apple-touch-icon-precomposed" sizes="180x180"
          href="https://img3.doubanio.com/f/talion/8e7b9cbd097c02972c4191aa03fdb084524505c4/pics/icon/m_logo_180.png">
    <link rel="apple-touch-icon-precomposed" sizes="200x200"
          href="https://img3.doubanio.com/f/talion/7c6364aadf368dc0210173c940cfd0f64ceddc66/pics/icon/m_logo_200.png">
    <!-- For Android -->
    <link rel="icon" sizes="128x128"
          href="https://img3.doubanio.com/f/talion/b99497ff8538c54b9ba6f40867da932396ab2562/pics/icon/m_logo_152.png">
    <link rel="icon" sizes="192x192"
          href="https://img3.doubanio.com/f/talion/7c6364aadf368dc0210173c940cfd0f64ceddc66/pics/icon/m_logo_200.png">
    <!-- For Web App Manifest -->


    <link type="application/opensearchdescription+xml" rel="search" href="https://m.douban.com/opensearch">
    <!-- hm baidu -->
    <script async="" src="//www.googletagmanager.com/gtm.js?id=GTM-NZHN7H"></script>
    <script type="text/javascript" async="" src="https://www.google-analytics.com/analytics.js"></script>
    <script type="text/javascript"
            src="https://img3.doubanio.com/f/adjs/581c3c87bd224677f6207b2b3ba1e4a512cbb1dc/ad.release.js"
            async="true"></script>
    <script src="https://hm.baidu.com/hm.js?6d4a8cfea88fa457c3127e14fb5fabc2"></script>
    <script async="" src="//www.googletagmanager.com/gtm.js?id=GTM-NZHN7H"></script>
    <script type="text/javascript" async="" src="https://www.google-analytics.com/analytics.js"></script>
    <script type="text/javascript"
            src="https://img3.doubanio.com/f/adjs/581c3c87bd224677f6207b2b3ba1e4a512cbb1dc/ad.release.js"
            async="true"></script>
    <script src="https://hm.baidu.com/hm.js?6d4a8cfea88fa457c3127e14fb5fabc2"></script>
    <script type="text/javascript" async="" src="./十月的天空 - 图书 - 豆瓣_files/analytics.js"></script>
    <script async="" src="./十月的天空 - 图书 - 豆瓣_files/gtm.js"></script>
    <script type="text/javascript" src="./十月的天空 - 图书 - 豆瓣_files/ad.release.js" async="true"></script>
    <script src="./十月的天空 - 图书 - 豆瓣_files/hm.js"></script>
    <script type="text/javascript">
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?6d4a8cfea88fa457c3127e14fb5fabc2";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body>
<div class="page">
    <section class="promo_top_banner" style="display: block !important">
        <div class="banner_bg">
            <img src="./十月的天空 - 图书 - 豆瓣_files/promotion_bg.jpg" class="img">
        </div>
        <div class="banner_wrapper">
            <div class="banner_inner">
                <div class="promo_title">
                    <span class="promo_title_text">用 App 打开</span><br>
                    <span class="promo_title_text">更多文学好书推荐导读</span>
                </div>
                <div class="button_wrapper">
                    <a href="https://www.douban.com/doubanapp/app?model=B&amp;copy=1&amp;page=book&amp;channel=card_book&amp;direct_dl=1"
                       rel="nofollow" onclick="ga('send', 'event', 'android', 'click', 'subject banner download')"
                       class="promo_button download_app">极速下载</a>
                    <a href="https://www.douban.com/doubanapp/card/get_app?from=mdouban&amp;client_uri=douban%3A%2F%2Fdouban.com%2Fbook%2F1334151&amp;model=B&amp;copy=1&amp;page=book&amp;channel=card_book"
                       rel="nofollow" onclick="ga('send', 'event', 'android', 'click', 'card more link')"
                       class="promo_button open_app">打开</a>
                </div>
            </div>
        </div>
    </section>
    <div class="card">
        <h1 class="title">十月的天空</h1>
        <section class="subject-info">
            <div class="right">
                <a href="https://www.douban.com/doubanapp/card/get_app?client_uri=douban://douban.com/book/1334151&amp;from=mdouban&amp;channel=card_book_cover"
                   rel="nofollow" onclick="ga('send', 'event', 'android', 'click', 'card more link')">
                    <img src="./十月的天空 - 图书 - 豆瓣_files/s3846387.jpg" alt="十月的天空" class="cover">
                </a>
            </div>
            <div class="left">
                <p class="rating">
                    <span class="rating-stars" data-rating="86.0"><span
                            class="rating-star rating-star-medium-full"></span><span
                            class="rating-star rating-star-medium-full"></span><span
                            class="rating-star rating-star-medium-full"></span><span
                            class="rating-star rating-star-medium-full"></span><span
                            class="rating-star rating-star-medium-half"></span></span>
                    <strong>8.6</strong>
                    <span>40人评价</span>
                </p>
                <p class="meta">
                    <a href="https://m.douban.com/book/author/943291" title="希坎姆" class="author">
                        希坎姆
                    </a> /

                    陳可崗

                    /
                    天下文化 / NT$ 320 / 1999年10月15日
                </p>
            </div>
        </section>
        <div class="vendors-link-group">
            <div id="douban-vendors"><!-- react-empty: 1 --></div>
        </div>
        <input id="user" type="hidden" value="">
        <section class="subject-intro">
            <h2>十月的天空的内容简介</h2>
            <div class="bd" style="position: static;">
                <p data-clamp="3"
                   data-content="<br>十月的天空<br>一九五七年十月五日蘇聯發射人類第一枚人造衛星「同路一號」，當時美國西維吉尼亞洲的煤林鎮是一個逐漸凋零、死寂的小鎮。面對茫茫未知的前途，荷默再小小的心靈裡孵著一個夢：送火箭上太空。<br>擔任煤礦場監督的父親對荷默並沒有太大的期望，然而他的母親確堅決要讓他從此擺脫煤塵瀰漫的灰濛煤林鎮。荷默有一群同好，他們不屈不撓的研發與試射火箭，從一開使用金屬廢料七拼八湊，到最後製造出結構精密複雜的火箭，再這一路走來的奮鬥史中，充滿了青少年成長與實現夢想的歡笑與淚水，也展現出鍥而不捨的科學研究精神。就在熊熊火箭衝破陰霾天空的剎那，荷默與煤林鎮從此聲名遠播。<br>本書是美國航太總署退休工程師荷默˙希坎姆的回憶，他透過細膩的筆法，把衝突矛盾的親情、志同道合的友誼、春風化雨的師恩、情竇初開的戀愛交織成一部溫馨動人的故事。<br>">
                    十月的天空一九五七年十月五日蘇聯發射人類第一枚人造衛星「同路一號」，當時美國西維吉尼亞洲的煤林鎮是一個逐漸凋零、死寂的小鎮。面對茫茫未知的前途，荷默再小小的心靈裡孵著一個夢：送火箭上太空。擔任煤礦場監督的父親對荷默並沒有太大的期望，然...<a
                        class="expand" href="javascript:;" style="float:right;">(展开)</a></p>
            </div>
        </section>
        <section class="subject-comments">
            <h2>十月的天空的短评(12)</h2>
            <div class="bd" id="comment-list">
                <ul data-reactroot="" class="list comment-list">
                    <li class="">
                        <div class="desc"><a href="https://m.douban.com/people/54642868/"><img
                                src="./十月的天空 - 图书 - 豆瓣_files/up54642868-2.jpg" alt="A2O"></a>

                            <div class="user-info"><strong>A2O</strong><span class="rating-stars" data-rating="5"><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span></span>

                                <div class="date">2016-12-11 01:35:46</div>
                            </div>
                        </div>
                        <p>梦想</p>

                        <div class="btn-info">
                            <div class="ic-btn ic-btn-like  left "><span class="text">1</span></div>
                            <div class="ic-btn ic-btn-more   right"></div>
                        </div>
                    </li>
                    <li class="">
                        <div class="desc"><a href="https://m.douban.com/people/64521098/"><img
                                src="./十月的天空 - 图书 - 豆瓣_files/up64521098-1.jpg" alt="卷卷是开水烫哒"></a>

                            <div class="user-info"><strong>卷卷是开水烫哒</strong><span class="rating-stars"
                                                                                 data-rating="4"><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-gray"></span></span>
                                <div class="date">2017-03-15 12:54:43</div>
                            </div>
                        </div>
                        <p>关于梦想，男主略帅，男主的爸爸也略帅呀！</p>
                        <div class="btn-info">
                            <div class="ic-btn ic-btn-like  left "><span class="text">0</span></div>
                            <div class="ic-btn ic-btn-more   right"></div>
                        </div>
                    </li>
                    <li class="">
                        <div class="desc"><a href="https://m.douban.com/people/39763058/"><img
                                src="./十月的天空 - 图书 - 豆瓣_files/up39763058-1.jpg" alt="④湁⑤嬡"></a>

                            <div class="user-info"><strong>④湁⑤嬡</strong><span class="rating-stars" data-rating="5"><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span><span
                                    class="rating-star rating-star-medium-full"></span></span>

                                <div class="date">2016-12-14 17:15:59</div>
                            </div>
                        </div>
                        <p>超赞。对理想的呵护与坚持！</p>

                        <div class="btn-info">
                            <div class="ic-btn ic-btn-like  left "><span class="text">0</span></div>
                            <div class="ic-btn ic-btn-more   right"></div>
                        </div>
                    </li>
                    <li class="go-comment-list"><a
                            href="https://m.douban.com/book/subject/1334151/comments?from=subject">查看全部短评</a></li>
                    <!-- react-empty: 58 --><!-- react-empty: 59 --></ul>
            </div>
        </section>

        <div id="discussions-root">
            <div data-reactroot="" class="loading"></div>
        </div>


        <script id="template-theme-item" type="text/html">
            <li class="Theme-item {extra_className}">
                <a href="{url}" rel="nofollow">
                    <div>
                        <div class="Theme-item-image" data-src="{cover}" data-type="cover"></div>
                        <div class="Theme-item-info">
                            <h3>{title}</h3>

                            <p>{desc}</p>
                        </div>
                        <div class="Theme-item-action">
                            <div class="Theme-item-button">打开应用查看</div>
                        </div>
                    </div>
                </a>
            </li>
        </script>


        <div class="center">


            <div id="dale_talion_subject_book_bottom" class="Advertisement" ad-status="loaded" data-sell-type="CPC"
                 data-type="GDTRender" data-version="3.0.10"></div>


        </div>
    </div>


    <div class="download-app">
        <div class="info">
            <img src="./十月的天空 - 图书 - 豆瓣_files/douban-app-logo.png" width="48">

            <div class="info-content">
                <strong>豆瓣</strong>

                <div>我们的精神角落</div>
            </div>
        </div>
        <a href="https://www.douban.com/doubanapp/card/log?category=book&amp;cid=1334151&amp;action=click_download&amp;ref=http%3A//www.douban.com/doubanapp/app%3Fchannel%3Dcard_book%26direct_dl%3D1"
           rel="nofollow">免费下载 Android 客户端</a>
    </div>


    <div class="bottom_ad_download show">


        <div id="dale_tailon_book_bottom_floating_inner" class="Advertisement" ad-status="loaded"></div>


    </div>


</div>

<script src="./十月的天空 - 图书 - 豆瓣_files/zepto.min.js"></script>
<script src="./十月的天空 - 图书 - 豆瓣_files/main.js"></script>
<div id="toast" class="gray"><span>无网络连接</span></div>
<div id="toast" class="gray"><span>无网络连接</span></div>
<div id="toast"><span></span></div>


<script src="./十月的天空 - 图书 - 豆瓣_files/react-all.min.js"></script>

<script src="./十月的天空 - 图书 - 豆瓣_files/interest.es6"></script>
<script type="text/javascript">
    var TalionData = window.TalionData || (window.TalionData = {})
    TalionData.section = {
        vendors: []
    }

    TalionData.subject = {
        sid: '1334151',
        type: 'book'
    };
    TalionData.count = 4
    TalionData.hasMore = true
</script>


<script type="text/javascript" src="./十月的天空 - 图书 - 豆瓣_files/74a60bedf337339.js"></script>
<div id="pop-page_1">
    <div data-reactroot="" class="pop-page"><!-- react-empty: 2 -->
        <div class="content">
            <section><h2 class="sec-title">电子书/纸质版购买</h2>
                <ul class="list vendor-list"></ul>
            </section>
        </div>
    </div>
</div>
<div>
    <div data-reactroot="" class="pop-page-header header "><span class="title">电子书/纸质版购买</span>

        <div class="btn btn-left ">关闭</div>
    </div>
</div>
<div id="pop-page_1">
    <div data-reactroot="" class="pop-page"><!-- react-empty: 2 -->
        <div class="content">
            <section><h2 class="sec-title">电子书/纸质版购买</h2>
                <ul class="list vendor-list"></ul>
            </section>
        </div>
    </div>
</div>
<div>
    <div data-reactroot="" class="pop-page-header header "><span class="title">电子书/纸质版购买</span>

        <div class="btn btn-left ">关闭</div>
    </div>
</div>
<div id="pop-page_1">
    <div data-reactroot="" class="pop-page"><!-- react-empty: 2 -->
        <div class="content">
            <section><h2 class="sec-title">电子书/纸质版购买</h2>
                <ul class="list vendor-list"></ul>
            </section>
        </div>
    </div>
</div>
<div>
    <div data-reactroot="" class="pop-page-header header "><span class="title">电子书/纸质版购买</span>

        <div class="btn btn-left ">关闭</div>
    </div>
</div>


<script type="text/javascript" data-mobile="true">
    (function (global) {
        var newNode = global.document.createElement('script'),
                existingNode = global.document.getElementsByTagName('script')[0],
                adSource = '//erebor.douban.com/',
                userId = '',
                browserId = 'VyuoCLoWIsY',
                criteria = '3:/book/subject/1334151/?from=singlemessage&amp;dt_platform=com.douban.activity.wechat_friends&amp;dt_dapp=1',
                preview = '',
                debug = false;

        global.DoubanAdRequest = {
            src: adSource,
            uid: userId,
            bid: browserId,
            crtr: criteria,
            prv: preview,
            debug: debug
        };

        newNode.setAttribute('type', 'text/javascript');
        newNode.setAttribute('src', 'https://img3.doubanio.com/f/adjs/581c3c87bd224677f6207b2b3ba1e4a512cbb1dc/ad.release.js');
        newNode.setAttribute('async', true);
        existingNode.parentNode.insertBefore(newNode, existingNode);
    })(this);
</script>

<script type="text/javascript">
    ;
    (function (global) {
        global.DoubanAdSlots = global.DoubanAdSlots || []
    })(window);
</script>
<!-- Google Tag Manager -->
<noscript>&amp;amp;lt;iframe src="//www.googletagmanager.com/ns.html?id=GTM-NZHN7H" height="0" width="0"
    style="display:none;visibility:hidden"&amp;amp;gt;&amp;amp;lt;/iframe&amp;amp;gt;
</noscript>
<script>(function (w, d, s, l, i) {
    w[l] = w[l] || [];
    w[l].push({'gtm.start': new Date().getTime(), event: 'gtm.js'});
    var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : '';
    j.async = true;
    j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl;
    f.parentNode.insertBefore(j, f);
})(window, document, 'script', 'dataLayer', 'GTM-NZHN7H');</script>
<!-- End Google Tag Manager -->
<!-- Google Analytics -->
<script>
    window.ga = window.ga || function () {
                (ga.q = ga.q || []).push(arguments)
            };
    ga.l = +new Date;
    ga('create', 'UA-53594431-3', {'sampleRate': 4});
    ga('send', 'pageview');
</script>
<script async="" src="./十月的天空 - 图书 - 豆瓣_files/analytics.js"></script>
<!-- End Google Analytics -->


<div id="pop-menu_1">
    <div data-reactroot="" class="pop-menu-wrapper ">
        <div class="pop-menu">
            <div class="item">举报</div>
        </div>
    </div>
</div>
<div id="dialog_1">
    <div data-reactroot="" class="dialog-container   report-dialog">
        <div class="dialog-overlay"></div>
        <div class="dialog-wrapper">
            <div class="dialog">
                <div class="reason-item">广告或垃圾信息</div>
                <div class="reason-item">色情或低俗内容</div>
                <div class="reason-item">激进时政或意识形态话题</div>
                <div class="reason-item">其他原因</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>