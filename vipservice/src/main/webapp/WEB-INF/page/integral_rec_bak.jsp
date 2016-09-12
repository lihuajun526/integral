<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="" lang="zh-cmn-Hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="cleartype" content="on">
    <meta name="referrer" content="always">
    <link rel="shortcut icon" href="https://b.yzcdn.cn/v2/image/yz_fc.ico">
    <title>积分记录</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <style>@charset "UTF-8";
    a, body {
        color: #333
    }

    .clearfix, .container .content {
        zoom: 1
    }

    .copyright .ft-links ~ .ft-copyright, hr {
        border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line.png) 2 stretch
    }

    a, abbr, acronym, address, applet, article, aside, audio, b, big, blockquote, body, canvas, caption, center, cite, code, dd, del, details, dfn, div, dl, dt, em, embed, fieldset, figcaption, figure, footer, form, h1, h2, h3, h4, h5, h6, header, hgroup, html, i, iframe, img, ins, kbd, label, legend, li, mark, menu, nav, object, ol, output, p, pre, q, ruby, s, samp, section, small, span, strike, strong, sub, summary, sup, table, tbody, td, tfoot, th, thead, time, tr, tt, u, ul, var, video {
        margin: 0;
        padding: 0;
        border: 0;
        font: inherit;
        font-size: 100%;
        vertical-align: baseline
    }

    html {
        line-height: 1;
        -ms-text-size-adjust: 100%;
        -webkit-text-size-adjust: 100%;
        font-family: Helvetica, "STHeiti STXihei", "Microsoft JhengHei", "Microsoft YaHei", Tohoma, Arial
    }

    ol, ul {
        list-style: none
    }

    table {
        border-collapse: collapse;
        border-spacing: 0
    }

    caption, td, th {
        font-weight: 400;
        vertical-align: middle
    }

    blockquote, q {
        quotes: none
    }

    blockquote:after, blockquote:before, q:after, q:before {
        content: "";
        content: none
    }

    .arrow-right::after, .clearfix:after, .container .content:after {
        content: ''
    }

    a img {
        border: none
    }

    article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section, summary {
        display: block
    }

    body {
        background-color: #eee;
        -webkit-backface-visibility: hidden
    }

    body.body-fixed-bottom {
        padding-bottom: 40px
    }

    .container {
        background-color: #f8f8f8
    }

    strong {
        font-weight: 700
    }

    a {
        background: 0 0;
        text-decoration: none;
        -webkit-tap-highlight-color: transparent
    }

    h1 {
        font-size: 2em;
        margin: .67em 0
    }

    h2 {
        font-size: 18px;
        line-height: 22px
    }

    h3 {
        font-size: 15px;
        line-height: 18px
    }

    button, input[type=number], input[type=text], input[type=password], input[type=email], input[type=search], select, textarea {
        font-family: inherit;
        font-size: 100%;
        margin: 0;
        -webkit-tap-highlight-color: transparent;
        -webkit-appearance: none;
        -moz-appearance: none
    }

    .c-orange {
        color: #f60 !important
    }

    .c-orange-dark {
        color: #f15a0c !important
    }

    .c-green {
        color: #06bf04 !important
    }

    .c-red {
        color: #ed5050 !important
    }

    .c-pink {
        color: #ee614b !important
    }

    .c-white {
        color: #fff !important
    }

    .c-gray-light {
        color: #e5e5e5 !important
    }

    .c-gray {
        color: #c9c9c9 !important
    }

    .c-gray-dark {
        color: #999 !important
    }

    .c-gray-darker {
        color: #666 !important
    }

    .c-black {
        color: #333 !important
    }

    .c-yellow {
        color: #f09000 !important
    }

    .c-light-yellow {
        color: #fcff00 !important
    }

    .c-blue {
        color: #38f !important
    }

    hr {
        margin: 10px 0;
        border: 0;
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line.png) 2 stretch;
        border-top: 2px solid #e5e5e5
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        hr {
            border-top-width: 1px
        }
    }

    hr.margin-0 {
        margin: 0
    }

    hr.left-10 {
        margin-left: 10px
    }

    .relative {
        position: relative
    }

    .font-size-12 {
        font-size: 12px !important
    }

    .font-size-14 {
        font-size: 14px !important
    }

    .font-size-16 {
        font-size: 16px !important
    }

    .font-size-18 {
        font-size: 18px !important
    }

    .font-size-20 {
        font-size: 20px !important
    }

    .font-size-22 {
        font-size: 22px !important
    }

    .font-size-24 {
        font-size: 24px !important
    }

    .font-size-26 {
        font-size: 26px !important
    }

    .font-size-28 {
        font-size: 28px !important
    }

    .ellipsis {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis
    }

    .border-bottom-none {
        border-bottom: 0 !important
    }

    .border-top-none {
        border-top: 0 !important
    }

    .link, .link:active, .link:hover, .link:link, .link:visited {
        color: #07d
    }

    .clearfix:after {
        display: table;
        clear: both
    }

    .pull-left {
        float: left
    }

    .pull-right {
        float: right
    }

    .show {
        display: block
    }

    .hide {
        display: none !important;
        visibility: hidden
    }

    .center, .text-center {
        text-align: center
    }

    .text-left {
        text-align: left
    }

    .text-right {
        text-align: right
    }

    #right-icon .icon-txt, .btn, .btn-block, .copyright, .footer, .fullscreen-guide {
        text-align: center
    }

    .loading {
        background-image: url(https://b.yzcdn.cn/v2/image/loader.gif) !important;
        background-repeat: no-repeat !important;
        background-position: center center !important;
        background-size: 16px 16px
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .loading {
            background-image: url(https://b.yzcdn.cn/v2/image/loader@2x.gif) !important
        }
    }

    .with-bs-bottom, .with-bs-top {
        background: url(https://b.yzcdn.cn/v2/image/wap/bottom_line.png) left top no-repeat;
        background-size: 100% 1px
    }

    .with-bs-bottom {
        background-position: left bottom
    }

    .arrow-right::after {
        position: absolute;
        width: 7px;
        height: 7px;
        border-top: 2px solid #cbc9cc;
        border-right: 2px solid #cbc9cc;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        top: 50%;
        right: 3px;
        margin-top: -5px
    }

    .content, .wrapper {
        margin: 0 auto
    }

    .wrapper {
        width: 320px
    }

    .container .content:after {
        display: table;
        clear: both
    }

    .copyright {
        background-color: #f8f8f8;
        color: #999;
        font-size: 12px;
        line-height: 16px
    }

    .copyright .ft-links {
        padding: 15px
    }

    .copyright .ft-links > a {
        margin: 0 6px;
        color: #333
    }

    .copyright .ft-links ~ .ft-copyright {
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line.png) 2 stretch;
        border-top: 2px solid #e5e5e5
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .copyright .ft-links ~ .ft-copyright {
            border-top-width: 1px
        }
    }

    .copyright .ft-copyright {
        padding: 50px 0 20px;
        margin: 0 15px;
        font-size: 12px;
        background: url(https://b.yzcdn.cn/v2/image/wap/common/logo@2x.png) center 20px no-repeat;
        background-size: 73px 24px
    }

    .copyright .ft-copyright > a {
        padding-top: 45px;
        color: #ccc
    }

    .copyright .pf-copyright {
        display: block;
        background: url(https://b.yzcdn.cn/v2/image/pf/ui/copyright.png) center 20px no-repeat;
        background-size: 73px 24px
    }

    .footer {
        margin: 0;
        padding: 0;
        min-height: 1px;
        line-height: 16px;
        background-color: #f8f8f8
    }

    .footer .link-icon {
        display: inline-block;
        width: 16px;
        height: 16px;
        margin-bottom: -3px;
        margin-right: 10px;
        background-image: url(data:image/png;base64,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);
        background-repeat: no-repeat;
        background-size: 16px
    }

    .footer .link-title {
        font-size: 16px
    }

    .full-screen .footer {
        margin-top: 0
    }

    .btn {
        display: inline-block;
        border-radius: 3px;
        padding: 5px 4px;
        margin: 0;
        font-size: 12px;
        cursor: pointer;
        line-height: 1.5;
        -webkit-appearance: none;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        color: #999
    }

    .fullscreen-guide.hide, .motify {
        display: none
    }

    .btn-disabled, .btn[disabled] {
        background-color: #c9c9c9 !important;
        background-image: none !important;
        border: 1px solid transparent !important;
        color: #fff !important
    }

    .btn-disabled:active, .btn[disabled]:active {
        background-color: #c9c9c9 !important;
        background-image: none !important;
        border: 1px solid #bcbcbc !important
    }

    .btn-default {
        color: #999;
        background-color: #fff;
        border: 1px solid #e5e5e5
    }

    .btn-blue {
        color: #fff;
        background-color: #38f;
        border-color: #38f
    }

    .btn-gray-dark {
        color: #333;
        background-color: #999;
        border-color: #999
    }

    .btn-green {
        color: #fff;
        background-color: #06bf04;
        border-color: #03b401
    }

    .btn-red {
        color: #fff;
        background-color: #ed5050;
        border-color: #ed5050
    }

    .btn-pink {
        color: #fff;
        background-color: #ee614b;
        border-color: #ee614b
    }

    .btn-white {
        color: #333;
        background-color: #fff;
        border-color: #e5e5e5
    }

    .btn-yellow {
        color: #fff;
        background-color: #f09000;
        border-color: #f09000
    }

    .btn-grayeee {
        color: #999;
        background-color: #eee;
        border-color: 1px solid #ebebeb
    }

    .btn-orange {
        color: #fff;
        border-color: #f60;
        background-color: #f60
    }

    .btn-orange-dark {
        color: #fff;
        border-color: #f15a0c;
        background-color: #f15a0c
    }

    .btn-block {
        width: 100%;
        padding: 11px 10px;
        font-size: 16px;
        line-height: 16px;
        border-radius: 4px;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box
    }

    .btn-xsmall, .btn-xxsmall {
        line-height: 14px;
        width: auto;
        font-size: 12px
    }

    .btn-xsmall {
        padding: 8px 10px
    }

    .btn-xxsmall {
        padding: 4px 10px
    }

    .btn-l, .motify {
        font-size: 14px
    }

    .btn-l {
        padding: 9px 4px
    }

    .btn-opt {
        min-width: 50px
    }

    .motify {
        position: fixed;
        top: 35%;
        left: 50%;
        width: 220px;
        padding: 0;
        margin: 0 0 0 -110px;
        z-index: 9999;
        background: rgba(0, 0, 0, .8);
        color: #fff;
        line-height: 1.5em;
        border-radius: 6px;
        -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
        box-shadow: 0 1px 2px rgba(0, 0, 0, .2)
    }

    .motify .motify-inner {
        padding: 10px;
        text-align: center;
        word-wrap: break-word
    }

    .motify p {
        margin: 0 0 5px
    }

    .motify p:last-of-type {
        margin-bottom: 0
    }

    .fullscreen-guide {
        z-index: 2000;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .9)
    }

    .fullscreen-guide .guide-close {
        position: absolute;
        top: 0;
        left: 10px;
        font-size: 40px;
        color: #fff;
        cursor: pointer
    }

    .fullscreen-guide .guide-arrow {
        position: absolute;
        top: 2px;
        right: 15px;
        background: url(https://b.yzcdn.cn/v2/image/wap/guide_arrow.png) top left no-repeat;
        width: 47px;
        height: 44px
    }

    .fullscreen-guide .guide-inner {
        padding-top: 48px;
        width: 320px;
        margin: 0 auto
    }

    .fullscreen-guide .step {
        width: 260px;
        display: inline-block;
        text-align: left;
        margin-bottom: 15px
    }

    .follow-guide .guide-inner-title {
        color: #fff;
        border-bottom: 1px solid #666;
        margin: 10px;
        padding-bottom: 20px;
        font-size: 18px;
        font-weight: 400
    }

    .follow-guide .wxid {
        display: inline-block;
        text-align: left;
        margin-bottom: 20px;
        font-size: 18px
    }

    .follow-guide .wxid .hasno-mp-weixin {
        width: 200px;
        height: 200px;
        margin-top: 20px;
        background: url(https://b.yzcdn.cn/v2/image/wap/cannot_follow@2x.png) no-repeat;
        background-size: cover
    }

    .follow-guide .wxid strong {
        padding: 1px 5px;
        background-color: #fff;
        color: #000;
        border-radius: 4px
    }

    .follow-guide .guide-text {
        color: #fff;
        font-size: 16px;
        padding-bottom: 20px;
        border-bottom: 1px solid #666
    }

    .follow-guide .guide-text-title {
        color: #fff;
        margin: 20px 0;
        font-size: 20px
    }

    .follow-guide .guide-text-step {
        text-align: left;
        padding-left: 56px;
        color: #fff;
        margin-bottom: 15px;
        font-size: 16px
    }

    .follow-guide .step-1, .follow-guide .step-2, .follow-guide .step-3 {
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none
    }

    .follow-guide .step-1 {
        height: 29px
    }

    .follow-guide .step-2 {
        margin-bottom: 10px;
        height: 64px;
        background: url(https://b.yzcdn.cn/v2/image/wap/step_2.png) no-repeat
    }

    .follow-guide .step-3 {
        margin-top: 10px;
        height: 68px;
        background: url(https://b.yzcdn.cn/v2/image/wap/step_3.png) no-repeat
    }

    .follow-guide .quick-subscribe {
        margin: 140px 15px 0;
        padding: 10px;
        background: #fff;
        border: 2px solid #f2f2f2;
        border-radius: 10px
    }

    .follow-guide .quick-subscribe h2 {
        font-size: 14px;
        border-bottom: 1px solid #f2f2f2;
        padding-bottom: 20px;
        margin: 12px 0
    }

    .follow-guide .quick-subscribe .btn {
        font-size: 14px;
        background: #4b0;
        color: #fff;
        display: inline-block;
        line-height: 38px;
        padding: 0 26px;
        border-radius: 4px
    }

    #right-icon, .fixed-icon {
        border-radius: 26px;
        position: fixed;
        right: 6px;
        z-index: 10;
        width: 50px
    }

    .fuck-taobao .step-1 {
        height: 29px;
        background: url(https://b.yzcdn.cn/v2/image/wap/step_1.png) no-repeat
    }

    .fuck-taobao .step-2 {
        height: 86px;
        background: url(https://b.yzcdn.cn/v2/image/wap/ftios_step_2.png) no-repeat
    }

    .fuck-taobao .step-and-2 {
        height: 62px;
        background: url(https://b.yzcdn.cn/v2/image/wap/ftand_step_2.png) no-repeat
    }

    .fav-guide .step-1 {
        height: 29px;
        background: url(https://b.yzcdn.cn/v2/image/wap/step_1.png) no-repeat
    }

    .fav-guide .step-2 {
        height: 86px;
        background: url(https://b.yzcdn.cn/v2/image/wap/fav_step_2.png) no-repeat
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (-o-min-device-pixel-ratio: 3/2), only screen and (min-device-pixel-ratio: 1.5) {
        .fullscreen-guide .guide-arrow {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/guide_arrow@2x.png);
            background-size: 47px 44px
        }

        .follow-guide .step-2 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/step_2@2x.png);
            background-size: 260px 64px
        }

        .follow-guide .step-3 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/step_3@2x.png);
            background-size: 260px 68px
        }

        .fuck-taobao .step-1 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/step_1@2x.png);
            background-size: 260px 29px
        }

        .fuck-taobao .step-2 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/ftios_step_2@2x.png);
            background-size: 248px 86px
        }

        .fuck-taobao .step-and-2 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/ftand_step_2@2x.png);
            background-size: 260px 62px
        }

        .fav-guide .step-1 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/step_1@2x.png);
            background-size: 260px 29px
        }

        .fav-guide .step-2 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/fav_step_2@2x.png);
            background-size: 260px 86px
        }

        .share-guide .step-1 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/step_1@2x.png);
            background-size: 260px 29px
        }

        .share-guide .step-2 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/share_step_2@2x.png);
            background-size: 260px 86px
        }
    }

    a#global-cart {
        z-index: 10;
        display: block
    }

    a#global-cart .icon-img {
        background: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite_29025c38a6.png) -202px -4px no-repeat;
        background-size: 300px 150px
    }

    a#global-cart.hide {
        display: none
    }

    a#global-cart.new .icon-img {
        background-position: -250px -4px
    }

    .no-text a#global-cart {
        background: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite_29025c38a6.png) no-repeat;
        background-size: 300px 150px
    }

    .no-text a#global-cart.s0 {
        background-position: -200px -100px
    }

    .no-text a#global-cart.s1 {
        background-position: -150px -100px
    }

    .no-text a#global-cart.s2 {
        background-position: -100px -100px
    }

    .no-text a#global-cart.s3 {
        background-position: -50px -100px
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .no-text a#global-cart, a#global-cart .icon-img {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite@2x_29025c38a6.png)
        }

        a#global-cart .icon-img {
            background-position: -200px -7px
        }

        a#global-cart.new .icon-img {
            background-position: -250px -7px
        }
    }

    .fixed-icon {
        height: 50px;
        bottom: 52px;
        cursor: pointer
    }

    .ds-btn {
        bottom: 115px
    }

    .ds-btn .icon-img {
        height: 100%;
        background-image: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite_e4444b6200.png);
        background-repeat: no-repeat;
        background-size: 300px 150px;
        background-position: -250px -100px
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .ds-btn .icon-img {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite@2x_6b9819545.png)
        }
    }

    a#global-wish {
        z-index: 10;
        display: block
    }

    #right-icon.no-text .icon-img, #right-icon.no-text .icon-txt, a#global-wish.hide {
        display: none
    }

    a#global-wish .icon-img {
        background: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite_29025c38a6.png) -2px -4px no-repeat;
        background-size: 300px 150px
    }

    a#global-wish.new .icon-img {
        background-position: -45px -7px
    }

    .no-text a#global-wish {
        background: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite_29025c38a6.png) -150px -50px;
        background-size: 300px 150px
    }

    .no-text a#global-wish.new {
        background-position: -200px -50px
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .no-text a#global-wish, a#global-wish .icon-img {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite@2x_29025c38a6.png)
        }

        a#global-wish .icon-img {
            background-position: 3px -7px
        }
    }

    #right-icon {
        height: 50px;
        border: 1px solid #e1e1e1;
        bottom: 52px;
        overflow: hidden;
        -webkit-transition: width ease-in-out .3s;
        -moz-transition: width ease-in-out .3s;
        transition: width ease-in-out .3s;
        background-color: #fff;
        background-clip: padding-box
    }

    #right-icon .right-icon-container {
        position: absolute;
        height: 50px;
        right: 0;
        top: 0
    }

    #right-icon .icon {
        float: left;
        width: 50px;
        height: 50px
    }

    #right-icon .show-more-btn {
        background-image: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite_29025c38a6.png);
        background-repeat: no-repeat;
        background-size: 300px 150px;
        background-position: 1px -50px
    }

    #right-icon .show-more-btn.new {
        background-position: -99px -50px
    }

    #right-icon .icon-img {
        height: 32px
    }

    #right-icon .icon-txt {
        line-height: 18px;
        font-size: 12px;
        color: #9f9f9f
    }

    #right-icon.show {
        padding-left: 12px
    }

    #right-icon.show .show-more-btn {
        background-position: -49px -50px
    }

    #right-icon.no-text {
        border: 0;
        background-color: transparent
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        #right-icon .show-more-btn {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/right_icon/icons_sprite/icons_sprite@2x_29025c38a6.png)
        }
    }</style>
    <link rel="stylesheet" href="/statics/integral_rec/order_list_e14c8ea5e82fd500d7f78f60009a92a8.css"
          onerror="_cdnFallback(this)" media="screen">
</head>
<body>

<div class="container " style="min-height: 557px;">
    <div class="content js-page-content">

        <div class="tabber tabber-n4 tabber-double-11 clearfix">
            <a class="" href="https://wap.koudaitong.com/v2/trade/cart?source=weixin11&amp;kdt_id=17984819">购物车</a>
            <a class="active"
               href="https://wap.koudaitong.com/v2/trade/record/index?source=weixin11&amp;kdt_id=17984819">购物记录</a>
            <a class=""
               href="https://wap.koudaitong.com/v2/trade/record/luckymoney?source=weixin11&amp;kdt_id=17984819">我的红包</a>
            <a class=""
               href="https://wap.koudaitong.com/v2/trade/record/backs?source=weixin11&amp;kdt_id=17984819">我的返现</a>
        </div>
        <div id="order-list-container">

            <div class="js-list b-list">
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=54023"><span
                                    class="font-size-14">店铺：罗辑思维</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易关闭</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160822180233063571819</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160822180233063571819&amp;kdt_id=54023">
                        <div class="thumb">
                            <img src="/statics/integral_rec/Fml1cxHbZRzWPQn5zFy0nqgy9rMu.jpg!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">【预售9月8日发货】《细节》：如何轻松影响他人</h3>

                            <p class="sku-detail ellipsis js-toggle-more">
                <span class="c-gray-darker">
                    
                    
                    
                        《细节》&nbsp;
                        
                    
                </span>

                            </p>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>58.00</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  ">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥58.00</span>

                        </div>
                    </div>


                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=820980"><span
                                    class="font-size-14">店铺：有赞美妆旗舰店</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易关闭</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160715215707063550219</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160715215707063550219&amp;kdt_id=820980">
                        <div class="thumb">
                            <img src="/statics/integral_rec/Fh1ZR74CpUm0s85svgQuU-MQ3oQd.png!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">虚拟商品（购买时无需填写收货地址，测试商品，不发货，不...</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>1.00</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  ">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥1.00</span>

                        </div>
                    </div>


                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=820980"><span
                                    class="font-size-14">店铺：有赞美妆旗舰店</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易关闭</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160715214611063556604</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160715214611063556604&amp;kdt_id=820980">
                        <div class="thumb">
                            <img src="/statics/integral_rec/Fh1ZR74CpUm0s85svgQuU-MQ3oQd.png!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">虚拟商品（购买时无需填写收货地址，测试商品，不发货，不...</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>1.00</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  ">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥1.00</span>

                        </div>
                    </div>


                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=16198908"><span
                                    class="font-size-14">店铺：乐享券</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易完成</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160319123411063528111</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160319123411063528111&amp;kdt_id=16198908">
                        <div class="thumb">
                            <img src="/statics/integral_rec/FgeYcQaWFJYeqKlTaone2uDPNOLt.jpg!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">爱奇艺黄金会员（包天）</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>0.01</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  has-bottom-btns">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥0.01</span>

                        </div>
                    </div>


                    <div class="bottom">
                        <div class="opt-btn pull-right">
                            <a class="btn btn-default btn-in-order-list"
                               href="https://trade.koudaitong.com/trade/order/result?order_no=E20160319123411063528111&amp;kdt_id=16198908">物流</a><a
                                class="btn btn-default btn-in-order-list"
                                href="https://wap.koudaitong.com/v2/trade/reviews/readyReviewsList?order_no=E20160319123411063528111&amp;kdt_id=16198908">评价</a>
                        </div>
                    </div>

                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=1026355"><span
                                    class="font-size-14">店铺：有赞店铺认证</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易关闭</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160310165526063594637</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/wxpay/confirmQr?qr_id=2799429&amp;kdt_id=1026355&amp;showwxpaytitle=1">
                        <div class="thumb">
                            <img src="/statics/integral_rec/cashier_order.jpg!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">[乐享券]的实名认证收款</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>0.01</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  ">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥0.01</span>

                        </div>
                    </div>


                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=16166785"><span
                                    class="font-size-14">店铺：乐享积分生活</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易完成</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160308173331028496256</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160308173331028496256&amp;kdt_id=16166785">
                        <div class="thumb">
                            <img src="/statics/integral_rec/Fh1ZR74CpUm0s85svgQuU-MQ3oQd.png!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">虚拟商品（购买时无需填写收货地址，测试商品，不发货，不...</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>0.01</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  has-bottom-btns">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥0.01</span>

                        </div>
                    </div>


                    <div class="bottom">
                        <div class="opt-btn pull-right">
                            <a class="btn btn-default btn-in-order-list"
                               href="https://trade.koudaitong.com/trade/order/result?order_no=E20160308173331028496256&amp;kdt_id=16166785">物流</a><a
                                class="btn btn-default btn-in-order-list"
                                href="https://wap.koudaitong.com/v2/trade/reviews/readyReviewsList?order_no=E20160308173331028496256&amp;kdt_id=16166785">评价</a>
                        </div>
                    </div>

                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=16166785"><span
                                    class="font-size-14">店铺：乐享积分生活</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易完成</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160308153221028451571</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160308153221028451571&amp;kdt_id=16166785">
                        <div class="thumb">
                            <img src="/statics/integral_rec/FmFVBvckr7tkCLZkl87zRw8v9Xal.png!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">优酷会员（包天）</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>0.10</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  has-bottom-btns">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥0.10</span>

                        </div>
                    </div>


                    <div class="bottom">
                        <div class="opt-btn pull-right">
                            <a class="btn btn-default btn-in-order-list"
                               href="https://trade.koudaitong.com/trade/order/result?order_no=E20160308153221028451571&amp;kdt_id=16166785">物流</a><a
                                class="btn btn-default btn-in-order-list"
                                href="https://wap.koudaitong.com/v2/trade/reviews/readyReviewsList?order_no=E20160308153221028451571&amp;kdt_id=16166785">评价</a>
                        </div>
                    </div>

                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=16166785"><span
                                    class="font-size-14">店铺：乐享积分生活</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易关闭</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160308145309028499487</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160308145309028499487&amp;kdt_id=16166785">
                        <div class="thumb">
                            <img src="/statics/integral_rec/FmFVBvckr7tkCLZkl87zRw8v9Xal.png!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">优酷会员（包天）</h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>1.00</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  ">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥1.00</span>

                        </div>
                    </div>


                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=54023"><span
                                    class="font-size-14">店铺：罗辑思维</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易关闭</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20160308142612028499420</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20160308142612028499420&amp;kdt_id=54023">
                        <div class="thumb">
                            <img src="/statics/integral_rec/FuU6vrk5LLThytNRtdszkNcOtcpj.jpg!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">《文艺复兴三杰》 你的私人美术馆</h3>

                            <p class="sku-detail ellipsis js-toggle-more">
                <span class="c-gray-darker">
                    
                    
                    
                        平装版&nbsp;
                        
                    
                </span>

                            </p>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>368.00</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  ">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥368.00</span>

                        </div>
                    </div>


                </li>
                <li class="js-block-order block block-order animated">
                    <div class="header">
                        <div>
                            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=54023"><span
                                    class="font-size-14">店铺：罗辑思维</span></a>
                            <a class="order-state-str pull-right font-size-14" href="javascript:;">交易完成</a>
                        </div>
                        <div class="order-no font-size-12">订单编号：E20151101200745028464055</div>
                    </div>
                    <a class="name-card name-card-3col clearfix"
                       href="https://trade.koudaitong.com/trade/order/result?order_no=E20151101200745028464055&amp;kdt_id=54023">
                        <div class="thumb">
                            <img src="/statics/integral_rec/FtB09EDIejQZ5WrD_3nW5MnQyZhI.jpg!200x0.jpg">
                        </div>
                        <div class="detail">
                            <h3 class="font-size-14 l2-ellipsis">《经济学通识》 给我一双慧眼吧 </h3>


                        </div>
                        <div class="right-col">

                            <div class="price c-black">￥<span>58.00</span></div>

                            <div class="num c-gray-darker">
                                ×<span class="num-txt c-gray-darker">1</span>
                            </div>
                        </div>
                    </a>


                    <div class="bottom-price  has-bottom-btns">
                        <div class="pull-right">
                            合计：

                            <span class="c-orange">￥58.00</span>

                        </div>
                    </div>


                    <div class="bottom">
                        <div class="opt-btn pull-right">
                            <a class="btn btn-default btn-in-order-list"
                               href="https://trade.koudaitong.com/trade/order/result?order_no=E20151101200745028464055&amp;kdt_id=54023">物流</a>
                        </div>
                    </div>

                </li>
            </div>
        </div>
    </div>
</div>

<div class="footer" style="min-height: 86px;">
    <div class="copyright">
        <div class="ft-copyright">
            <a href="https://youzan.com/" target="_blank">有赞提供技术支持</a>
        </div>
    </div>

</div>


<script>
    "use strict";
    !function (n, e) {
        "function" == typeof define && define.amd ? define([], e(n, n.document)) : n.Loader = e(n, n.document)
    }(this, function (n, e) {
        function t(n) {
            return "complete" === n.readyState || "loaded" === n.readyState
        }

        function o(n, t, o) {
            var i = e.createElement("link");
            i.rel = "stylesheet", a(i, o, "css"), i.async = !0, i.href = n, s.appendChild(i)
        }

        function i(n, t, o) {
            var i = e.createElement("script");
            i.charset = "utf-8", a(i, o, "js"), i.async = !t.sync, i.src = n, s.appendChild(i)
        }

        function c(n, e) {
            var t;
            n.sheet && (t = !0), setTimeout(function () {
                t ? e() : c(n, e)
            }, 20)
        }

        function a(e, o, i) {
            function a() {
                e.onload = e.onreadystatechange = null, e = null, o()
            }

            var r = "onload"in e, u = "css" === i;
            return !u || !l && r ? void(r ? (e.onload = a, e.onerror = function () {
                e.onerror = null, n._cdnFallback(e)
            }) : e.onreadystatechange = function () {
                t(e) && a()
            }) : void setTimeout(function () {
                c(e, o)
            }, 1)
        }

        function r(n, e, t, c) {
            function a() {
                var t = e.indexOf(n);
                t > -1 && e.splice(t, 1), 0 === e.length && c()
            }

            f.test(n) ? o(n, t, a) : i(n, t, a)
        }

        function u(n, e, t) {
            var o = function () {
                t && t()
            };
            if (n = Array.prototype.slice.call(n || []), 0 === n.length)return void o();
            for (var i = 0, c = n.length; c > i; i++)r(n[i], n, e, o)
        }

        function d(e, o) {
            if (t(e))o(); else {
                var i = 1500, c = !1;
                n.addEventListener("load", function () {
                    c || (o(), c = !0)
                }), setTimeout(function () {
                    c || (o(), c = !0)
                }, i)
            }
        }

        var f = new RegExp("\\.css"), s = e.head || e.getElementsByTagName("head")[0], l = +navigator.userAgent.replace(/.*(?:AppleWebKit|AndroidWebKit)\/?(\d+).*/i, "$1") < 536, y = {
            async: function (n, t) {
                d(e, function () {
                    u(n, {}, t)
                })
            }, sync: function (n, t) {
                d(e, function () {
                    u(n, {sync: !0}, t)
                })
            }
        };
        return n.Loader = y, y
    });    </script>


<script>window.Loader.sync(["https:\/\/b.yzcdn.cn\/v2\/build\/wap\/common_08b03c7826.js", "https:\/\/b.yzcdn.cn\/v2\/build\/wap\/base_33619a7a85.js", "https:\/\/b.yzcdn.cn\/v2\/vendor\/u_b.js", "https:\/\/b.yzcdn.cn\/v2\/build\/wap\/uc\/order_record_819973fc23.js"]);
window.Loader.async(["\/\/hm.baidu.com\/hm.js?58fe4cc4b4af82caeb8bc08af32dd62c"]);</script>
</body>
</html>