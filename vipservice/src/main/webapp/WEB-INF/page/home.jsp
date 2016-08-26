<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.vip.integral.model.Goods" %>
<%@ page import="java.util.List" %>
<%
    List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
%>
<!DOCTYPE html>
<html class="admin responsive-320" lang="zh-cmn-Hans">
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
    <title>主页</title>
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
        background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAWJQAAFiUBSVIk8AAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNui8sowAAAAVdEVYdENyZWF0aW9uIFRpbWUAMS8yOC8xM4B+MooAAAZqSURBVFiFrZdPTFN7Fse/5/6hVan9R+vjWag1JUYSbSel9BplKLogExOsitZJlFa6cWPGdDZuVIwaXTiViSZuRoWVk7gYx817uxEX72pGYgsmT5GEEmHyNLVAWmXa3vbOgt5rW/DlAXOSbn733N/59PzO73zPJaxgTqdz244dOwa0Wm2AiPTKuiRJibm5ucGRkZGhld5bi1Htgt/vDzc0NDxgGAYMw1Q9k2UZpVIJhUIh8fr168Dk5GTy/wrQ1dUVtlgsD1iWBcuyYBgGRKQGl2UZxWIRpVIJkiQtTExM+MfGxuLrAWArg1ut1gc8z4PjOPVXCaP8iAhEpDWZTH/kOO7HDx8+/LIugH379gUaGxsfchwHnufVoD09PQgGgzh16hRsNhsymQzm5uaqIIxG47ogWADw+Xw/8jxvUIJv2rQJ58+fh8/ng16/VIONjY3Yu3cvUqkUZmdn1aMhIq1er9/z6dOnv3/+/Pm/qwVgOjs7AzzP25V0ExFCoRDsdjsAXAbwO1qKdhjASH9/PwRBAMMw6jFpNBpXW1vbubVkgNm8ebNbSTkRYevWrfB4PAvlwANEFC//08dE5AcwHIlE4HK5VAiWZaHT6frXBLB9+/bfK0UGAC0tLQAQVgJXmiAIfiIKAxg+ePDg0gYMA5ZlwXFc086dO7etGqCpqYmpvGqpVCpNRI8rnaxWqyEQCMRtNtu/jh49Gnc4HOccDsc/S6VSFYTFYlk9gNls3lgJMDY2tslmsxkqnXw+32BdXZ2rfEtc7e3tT0VR/IsgCCoAEaG9vf27VQN4PJ6fZFkGABARJEnSeL3ewUqnjRs31vE8D6VH8DzvunPnzu0TJ05AEATIsgwiwsmTJ7OrBti1a1dyz549KgDDMNiwYUOop6dnSHG6ffs2HA4HKiFYlnUNDAykg8EgBEFAS0sLXC7XqgFIluVtmUxm6urVq0in00qvR6FQwJcvX4afPHkSlmV5KJPJhGKxGGZmZlAqlVAqlSDLMsxmc3pgYMCUzWZx48YN15s3b/ZrNBo/wzAGAMjlcvHZ2dmh8fHxFVs2AYAsy0NTU1OhW7duYXFxEcViEYVCAcViEcVicZrjuB+OHDnS093d/X0sFsP79++rIIxGY/rChQs/RyKRlxqN5k/KkZT3hizLyOfzI6IoBj5+/Di/EoABQDyZTNpjsZgKIUkSisXi0lkxDLxeb7q/v99UC1H2+bm3t/eEKIp/m5mZ8VYCKD7FYnGZgKlqKMuyG8DTZDKpVyCUF5UiBYC2trb06dOnqyDKm8NoNKauXbvW8PDhQ7x48WIlAEiStPD27VsVokqOFYhsNqu/e/cuJiYm1BQq6QaAjo6OVDAYbKiFUGri8uXLJlEUkU6nEY/HkUqlVB9JklAoFFSIZQOJAgFAPz09jXg8roJotVoYDIZ/LywsHDt8+PAPBoNh50o1oRSmTqcDANy/fx+iKCpzBCRJQj6fn3727Jl7GUBFTTwG0LnScwB/7evrO+DxeL6PRCLLaqJUKkGn0/3n4sWLT6xW6x8A2O/du4fnz59XQWSz2eEVAWqyEQDgLy/NA3h68+bNf7x69SrJsiy8Xu83ayKXyw0/evQoLMvyEIBQLUQ+nwf3awBlQVp2fzs7O/02mw0AMDo6agKQjkajKkQZHjzP15X3CcuyjOPHj4cSiQQWFxcVAQNTu/lvsebm5npFgIgIo6Ojpvr6+kQ0GoXNZgMRobm5GYODXzs6EYV1Ot2w0+kE8FXA1gRw4MCB7yrnQwA4e/bs2fr6+sSlS5cQjUZx5coVWCyWfM2r53iez5eBllr/WgBCodBkQ0MDKkf3VCr1ZyzVSqK1tRUAEgCqpiQimp+cnFysFL81AQCYrxUwjuMO9fb2DhKRG0AXEbmJqKrtdnd3h1OplL5y7VeL8FtGRPH5+fn34+PjTcqAyjAMNBpN6NChQyhPTVUmCILfaDQOKkcGACaT6et3wWrt+vXrervd7n/58qWqFwDAMIzb6XR2mc3m+Vwu90tra6uwe/fu8JYtWx7wPK/lOE49tjNnziz/NPutVm5WT6emplyVKqo0GaU1K9lhWVb95iAi9PX1oaOjY3rNAGWIZQKmNCGlI1YC1AQHgK51AXwLolK81Ou2NGnh2LFjSvDTRDS0boBaiHfv3kEURbUjAoDZbIbb7cb+/fthtVrV4ADwP3+yVfhMKQFgAAAAAElFTkSuQmCC);
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
    <style>
        @charset "UTF-8";
        .tpl-shop-header {
            position: relative;
            height: 100px;
            background-repeat: no-repeat;
            background-position-x: center;
            background-size: cover
        }

        .tpl-shop-header:hover .close-modal {
            display: block
        }

        .tpl-shop-title {
            position: absolute;
            bottom: 10px;
            left: 100px;
            font-size: 16px;
            color: #FFF;
            text-shadow: 0 1px 2px rgba(0, 0, 0, .5)
        }

        .tpl-shop-avatar {
            position: absolute;
            left: 10px;
            bottom: -40px;
            width: 80px;
            height: 80px
        }

        .tpl-shop-avatar img {
            width: 80px;
            height: 80px;
            background-color: #E4E4E4;
            border-radius: 3px;
            -webkit-box-shadow: 0 0 1px 1px rgba(0, 0, 0, .2);
            box-shadow: 0 0 1px 1px rgba(0, 0, 0, .2)
        }

        .tpl-shop-content {
            padding: 7px 0;
            border: 1px solid #E4E4E4;
            background: #fff
        }

        .tpl-shop-content ul {
            margin: 0 0 0 86px;
            padding: 0
        }

        .tpl-shop-content li {
            float: left;
            width: 33%;
            list-style: none;
            border-right: 1px solid #E4E4E4;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            margin-top: 10px;
            margin-bottom: 6px
        }

        .tpl-shop-content li:last-child {
            border-right: 0 none
        }

        .tpl-shop-content li a {
            display: block;
            margin-top: -10px;
            margin-bottom: -6px
        }

        .tpl-shop-content span {
            display: block;
            font-size: 12px;
            text-align: center;
            color: #999
        }

        .tpl-shop-content .count {
            height: 21px;
            line-height: 21px;
            font-size: 18px;
            color: #333;
            overflow: hidden
        }

        .tpl-shop-content .count.l-5 {
            font-size: 16px
        }

        .tpl-shop-content .count.l-6 {
            font-size: 14px
        }

        .tpl-shop-content .count.l-7, .tpl-shop-content .count.l-8, .tpl-shop-content .count.l-9 {
            font-size: 12px
        }

        .tpl-shop-content .user {
            height: 18px;
            line-height: 18px;
            margin-bottom: 3px;
            background: url(https://b.yzcdn.cn/v2/image/me.png) center center no-repeat;
            background-size: contain
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .tpl-shop-content .user {
                background-image: url(https://b.yzcdn.cn/v2/image/me@2x.png)
            }
        }

        .custom-search {
            padding: 10px
        }

        .custom-search form {
            margin: 0;
            position: relative;
            border: 1px solid silver;
            background: #fff;
            border-radius: 4px;
            overflow: hidden
        }

        .custom-search form .custom-search-input {
            margin: 0;
            padding: 0 10px 0 40px;
            -webkit-appearance: none;
            -moz-appearance: none;
            border-radius: 0;
            height: 35px;
            width: 100%;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            border: 0;
            outline: 0;
            font-size: 14px;
            -webkit-box-shadow: 0 0;
            box-shadow: 0 0
        }

        .custom-search-button {
            position: absolute;
            top: 0;
            left: 0;
            border: 0;
            height: 35px;
            line-height: 35px;
            width: 37px;
            padding: 0;
            color: #fff;
            font-size: 14px;
            text-indent: -9999px;
            background: url(https://b.yzcdn.cn/v2/image/wap/search_icon.png) center center no-repeat #fff;
            background-size: 16px 16px;
            border-radius: 5px 0 0 5px
        }

        .custom-search-icon {
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABqUlEQVR42r3WzyvDcRzH8Y+vOHDkpBwpB46k3MwOfu0wLvgDyEEp/8DOrjat5MSJs7i4O7CaWClqshxELphf81QO65Vv7+++37ZXPVprfT7Py7e+c5VKxY+HMWSQwzMYn3xHBjF4cLXy+2ESFwiyC0xGDbdiE2G2idagYY0eIMoOJB4onMV/u0IKcQxgBIs4wjd02VrCE9C9YxXNcD6GcQPdRJCwh3M5WEYcLoBO6IN4Ds8Kx6BbM2KqB69yR8wKp+XANVrgarQu92xY4RM5kIILoVfuObXCJTkwChfSQ9U9j1b4U8L9EcL56ous8BOqNxQhXARj3GuFcxJeDhntwFfVPWdWOCPh45DhJbknbYXjYJEesHYU5Y64FfZwKYdu0RUw2oQdOX8Jzwr/moaugD4j2oZd6KZqeTttQVdGBoNogvvTjRWUoNsL8z4+hN/eUMQzrJWRtMIa30aY7UNfq0krrBIoIMgKSMDhM2jc+pc5jizyeAHjk+/IYlye3gWf+IwRjsKOa7ge5qHxD8xIuLFxCTc0nqhHTM1B46W6RjWu4UaZxR3uMfUDvUqknv4oBC4AAAAASUVORK5CYII=) center center no-repeat;
            background-size: 15px;
            height: 15px;
            width: 15px;
            display: inline-block;
            position: absolute;
            margin: auto;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            color: transparent
        }

        .custom-link-link .title, .custom-nav-title {
            overflow: hidden;
            text-overflow: ellipsis;
            width: 280px
        }

        .custom-link, .custom-nav {
            list-style: none;
            margin: 0;
            padding: 0
        }

        .custom-link li, .custom-nav li {
            height: 44px;
            background: url(https://b.yzcdn.cn/v2/image/wap/bottom_line.png) left bottom no-repeat;
            background-size: 100% 1px;
            margin: 0 10px;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box
        }

        .custom-link li:last-child, .custom-nav li:last-child {
            background-image: none
        }

        .custom-link li:first-child, .custom-nav li:first-child {
            background: url(https://b.yzcdn.cn/v2/image/wap/bottom_line.png) left bottom no-repeat;
            background-size: 100% 1px
        }

        .custom-link a, .custom-nav a {
            display: block;
            height: 100%;
            color: #333;
            line-height: 44px;
            font-size: 14px;
            font-weight: 700
        }

        .custom-link a:hover .right-arrow, .custom-nav a:hover .right-arrow {
            background-position: 0 0
        }

        .custom-link a:hover, .custom-nav a:hover {
            background-position: 0 15px
        }

        .custom-link .right-arrow, .custom-nav .right-arrow {
            padding-right: 0
        }

        .custom-nav-title {
            display: inline-block;
            white-space: nowrap
        }

        .custom-link .right-arrow {
            margin-top: 14px
        }

        .custom-link-link {
            height: 100%;
            line-height: 44px;
            display: block
        }

        .custom-link-link .title {
            float: left;
            margin: 0;
            line-height: 44px;
            height: 100%;
            font-size: 14px
        }

        .custom-link-img {
            float: right;
            display: block;
            max-width: 40px;
            max-height: 40px
        }

        .custom-link-img img {
            vertical-align: middle
        }

        @charset "UTF-8";
        .sc-goods-list.pic .goods-card.big-pic.card .link, .sc-goods-list.pic .goods-card.small-pic.card .link, .sc-goods-list.pic .goods-card.small-pic.promotion .link, .sc-goods-list.pic.size-4 .goods-card.multi-pic, .sc-goods-list.pic.waterfall .goods-card.small-pic .link {
            -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
            -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
            border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch
        }

        .sc-goods-list {
            font-size: 12px;
            padding: 5px;
            list-style: none;
            margin: 0
        }

        .sc-goods-list .goods-card {
            position: relative;
            margin: 0 5px
        }

        .sc-goods-list .link {
            display: block;
            background: #fff;
            min-height: 100px
        }

        .sc-goods-list .info.info-no-price p.goods-price-taobao, .sc-goods-list .info.info-no-title p.goods-title {
            display: none !important
        }

        .sc-goods-list .photo-block {
            text-align: center;
            overflow: hidden;
            position: relative;
            background-color: #f8f8f8;
            background-size: 6px 6px
        }

        .sc-goods-list .photo-block img {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            vertical-align: bottom;
            max-width: 100%
        }

        .sc-goods-list .info {
            position: relative
        }

        .sc-goods-list .info p {
            margin: 0
        }

        .sc-goods-list .info p.goods-title {
            line-height: 1.3;
            overflow: hidden;
            color: #333
        }

        .sc-goods-list .info p.goods-sub-title {
            word-break: break-all;
            line-height: 16px;
            padding-top: 0;
            color: #666;
            white-space: normal
        }

        .sc-goods-list .info p.goods-price {
            font-weight: 700;
            padding: 0
        }

        .sc-goods-list .info p.goods-price > em {
            font-style: normal;
            color: #f60
        }

        .sc-goods-list .info p.goods-price-taobao {
            color: #999;
            font-size: 12px;
            text-decoration: line-through
        }

        .sc-goods-list .info.info-no-price p.goods-price {
            opacity: 0
        }

        .sc-goods-list .buy-tag-space {
            height: 14px
        }

        .sc-goods-list .more-link, .sc-goods-list .more-link:active, .sc-goods-list .more-link:hover, .sc-goods-list .more-link:link, .sc-goods-list .more-link:visited {
            color: #07d
        }

        .sc-goods-list .empty-list .desc {
            margin-top: 50px;
            margin-bottom: 50px;
            font-size: 16px;
            color: #999
        }

        .sc-goods-list .empty-list .tag-home {
            padding: 0;
            width: 138px;
            height: 28px;
            line-height: 28px;
            font-size: 14px
        }

        .sc-goods-list .sold-out {
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            display: -webkit-box;
            display: -webkit-flex;
            display: -moz-box;
            display: -ms-flexbox;
            display: flex;
            color: #fff;
            background: url(//dn-kdt-img-test.qbox.me/public_files/2016/06/07/2bdf7cbffa23b7cbef3c4fc292112fee.png) center center no-repeat rgba(0, 0, 0, .3);
            background-size: auto 40%;
            text-indent: -999em
        }

        .sc-goods-list.pic .goods-card.normal .info .goods-buy, .sc-goods-list.pic .goods-card.normal .info .goods-price-taobao, .sc-goods-list.pic .goods-card.small-pic .info .goods-price-taobao, .sc-goods-list.pic .goods-card.small-pic.normal .info .goods-title, .sc-goods-list.pic .goods-card.small-pic.promotion .info .goods-title {
            display: none
        }

        .sc-goods-list .sold-out em {
            margin: auto
        }

        .sc-goods-list .goods-buy {
            position: absolute
        }

        .sc-goods-list .goods-buy.btn1, .sc-goods-list .goods-buy.btn2, .sc-goods-list .goods-buy.btn3, .sc-goods-list .goods-buy.btn4 {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/showcase-1416814739063.png);
            background-repeat: no-repeat
        }

        .sc-goods-list .goods-buy.btn1 {
            right: 10px;
            bottom: 8px;
            height: 25px;
            width: 30px;
            background-position: 0 0
        }

        .sc-goods-list .goods-buy.btn1.ajax-loading {
            right: 12px;
            bottom: 10px
        }

        .sc-goods-list .goods-buy.btn2 {
            right: 10px;
            bottom: 8px;
            height: 20px;
            width: 20px;
            background-position: 0 -88px
        }

        .sc-goods-list .goods-buy.btn2.ajax-loading {
            right: 12px;
            bottom: 10px
        }

        .sc-goods-list .goods-buy.btn3 {
            right: 1px;
            bottom: 8px;
            height: 25px;
            width: 40px;
            background-position: 0 -25px
        }

        .sc-goods-list .goods-buy.btn3.ajax-loading {
            right: 12px;
            bottom: 10px
        }

        .sc-goods-list .goods-buy.btn4 {
            right: 10px;
            bottom: 8px;
            height: 20px;
            width: 37px;
            background-position: 0 -68px
        }

        .sc-goods-list .goods-buy.btn4.ajax-loading {
            right: 22px;
            bottom: 10px
        }

        .sc-goods-list .goods-buy.ajax-loading {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/common/loading.gif);
            width: 16px;
            height: 16px;
            background-size: 16px 16px;
            background-position: top left
        }

        .sc-goods-list .buy-response {
            position: absolute;
            right: 0;
            bottom: 0;
            height: 70px;
            width: 70px;
            opacity: 0
        }

        .sc-goods-list .goods-wish {
            position: absolute;
            top: 0;
            right: 10px;
            width: 34px;
            height: 23px;
            background-position: 0 -9px;
            background-repeat: no-repeat;
            background-size: 34px 74px
        }

        .sc-goods-list .goods-wish.added-wish {
            background-position: 0 -41px
        }

        .sc-goods-list .goods-wish.btn-wish {
            background-image: url(https://b.yzcdn.cn/v2/image/wap/goods_list/wish_list.png)
        }

        .sc-goods-list .wish-response {
            position: absolute;
            top: 0;
            right: 0;
            width: 50px;
            height: 50px;
            opacity: 0
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list .goods-buy.btn1, .sc-goods-list .goods-buy.btn2, .sc-goods-list .goods-buy.btn3, .sc-goods-list .goods-buy.btn4 {
                background-image: url(https://b.yzcdn.cn/v2/image/wap/showcase2x-1416814739063.png);
                background-repeat: no-repeat;
                background-size: 40px auto
            }

            .sc-goods-list .goods-buy.btn4 {
                background-position: 0 -50px
            }

            .sc-goods-list .goods-buy.ajax-loading {
                background-image: url(https://b.yzcdn.cn/v2/image/wap/common/loading.gif);
                background-size: 16px 16px;
                background-position: top left
            }

            .sc-goods-list .goods-wish.btn-wish {
                background-image: url(https://b.yzcdn.cn/v2/image/wap/goods_list/wish_list@2x.png)
            }

            .sc-goods-list .goods-wish.added-wish {
                background-position: 0 -40px
            }
        }

        .sc-goods-list.pic .goods-card {
            margin: 10px 0
        }

        .sc-goods-list.pic .goods-card .info {
            padding-left: 4px;
            margin-top: 10px
        }

        .sc-goods-list.pic .goods-card .info .goods-title {
            margin-bottom: 5px
        }

        .sc-goods-list.pic .goods-card .info .goods-price {
            float: left;
            margin: 0 10px 10px 0
        }

        .sc-goods-list.pic .goods-wish {
            top: 5px
        }

        .sc-goods-list.pic .goods-card.big-pic .photo-block img {
            position: relative;
            max-height: 500px
        }

        .sc-goods-list.pic .goods-card.big-pic .sold-out {
            background-size: auto 34%
        }

        .sc-goods-list.pic .goods-card.small-pic {
            width: 50%;
            float: left
        }

        .sc-goods-list.pic .goods-card.small-pic .photo-block {
            height: 143px
        }

        .sc-goods-list.pic .goods-card.small-pic .info {
            font-size: 13px
        }

        .sc-goods-list.pic .goods-card.small-pic .info .goods-title {
            height: 32px;
            overflow: hidden;
            word-break: break-all
        }

        .sc-goods-list.pic .goods-card.small-pic .goods-buy.btn3 {
            right: 1px
        }

        .sc-goods-list.pic .goods-card.small-pic .goods-buy.btn3.ajax-loading {
            right: 12px
        }

        .sc-goods-list.pic .goods-card.normal .photo-block {
            width: 100%
        }

        .sc-goods-list.pic .goods-card.normal .info {
            position: absolute;
            height: 22px;
            bottom: 0;
            right: 10px;
            margin: 5px 0;
            padding-left: 0;
            white-space: nowrap;
            overflow: hidden;
            background: rgba(0, 0, 0, .4);
            background-clip: border-box;
            border-radius: 2px
        }

        .sc-goods-list.pic .goods-card.normal .info .goods-title {
            float: left;
            margin: 0;
            padding-left: 10px;
            max-width: 70%;
            line-height: 22px;
            color: #fff;
            text-overflow: ellipsis;
            white-space: nowrap
        }

        .sc-goods-list.pic .goods-card.normal .info .goods-price {
            color: #fff;
            margin: 0;
            float: right;
            max-width: 70px;
            overflow: hidden;
            line-height: 22px;
            height: 22px;
            padding: 0 5px
        }

        .sc-goods-list.pic .goods-card.normal .info .goods-price em {
            color: #fff
        }

        .sc-goods-list.pic .goods-card.small-pic.normal .photo-block img {
            max-width: 100%;
            max-height: 192px
        }

        .sc-goods-list.pic .goods-card.small-pic.normal .link {
            position: relative;
            margin: 5px
        }

        .sc-goods-list.pic .goods-card.small-pic.normal .info {
            width: auto
        }

        .sc-goods-list.pic .goods-card.big-pic.normal {
            width: 100%
        }

        .sc-goods-list.pic .goods-card.big-pic.normal .link {
            margin: 5px 0
        }

        .sc-goods-list.pic .goods-card.big-pic.normal .photo-block {
            min-height: 100px
        }

        .sc-goods-list.pic .goods-card.big-pic.normal .info {
            left: 10px
        }

        .sc-goods-list.pic .goods-card.big-pic.normal .info.info-no-title {
            left: auto
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info, .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .link {
            background-color: #f9f9f9
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info {
            position: relative;
            height: auto;
            left: 0;
            right: 0
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info .goods-title {
            color: #333;
            font-size: 14px;
            font-weight: 700;
            min-width: 10px;
            min-height: 22px;
            padding-left: 0
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info .goods-price {
            position: absolute;
            right: 0;
            top: 0
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info .goods-price em {
            color: #f60;
            font-size: 14px
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info .goods-sub-title {
            clear: both
        }

        .sc-goods-list.pic .goods-card.big-pic.normal.has-sub-title .info.info-no-title.info-price {
            padding-top: 22px
        }

        .sc-goods-list.pic .goods-card.big-pic.card {
            margin: 8px 0;
            width: 100%;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box
        }

        .sc-goods-list.pic .goods-card.big-pic.card .link {
            margin: 0 4px;
            border-top: 2px solid #e5e5e5;
            border-right: 2px solid #e5e5e5;
            border-bottom: 2px solid #e5e5e5;
            border-left: 2px solid #e5e5e5
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.pic .goods-card.big-pic.card .link {
                border-width: 1px
            }
        }

        .sc-goods-list.pic .goods-card.big-pic.card .photo-block {
            min-height: 100px;
            margin: 4px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .info {
            margin-left: 4px;
            padding-right: 8px;
            min-height: 34px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .info .goods-title {
            font-size: 14px;
            font-weight: 700;
            text-overflow: ellipsis;
            white-space: nowrap;
            width: 85%
        }

        .sc-goods-list.pic .goods-card.big-pic.card .info .goods-sub-title {
            width: 100%;
            margin-bottom: 6px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .info .goods-price {
            font-size: 15px;
            margin-top: 2px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .info .goods-price-taobao {
            line-height: 17px;
            padding-top: 2px;
            padding-bottom: 8px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .info.info-no-price {
            min-height: 28px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.info-title.info-no-price.btn1 {
            bottom: 9px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.info-title.info-no-price.btn1.ajax-loading {
            bottom: 13.5px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.info-title.info-no-price.btn2 {
            bottom: 8px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.info-title.info-no-price.btn2.ajax-loading {
            bottom: 11.5px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.info-title.info-no-price.btn4.ajax-loading {
            bottom: 10.5px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn1 {
            bottom: 10px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn1.ajax-loading {
            bottom: 16.5px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn2 {
            bottom: 12px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn2.ajax-loading {
            bottom: 14.5px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn3 {
            bottom: 10px;
            right: 1px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn3.ajax-loading {
            bottom: 13px;
            right: 10px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn4 {
            bottom: 10px
        }

        .sc-goods-list.pic .goods-card.big-pic.card .goods-buy.btn4.ajax-loading {
            bottom: 13.5px
        }

        .sc-goods-list.pic .goods-card.big-pic.card.has-sub-title .info.info-no-price .goods-sub-title {
            padding-bottom: 35px
        }

        .sc-goods-list.pic .goods-card.big-pic.card.has-sub-title .info.btn0 .goods-sub-title {
            padding-bottom: 0
        }

        .sc-goods-list.pic .goods-card.small-pic.card {
            max-height: 230px;
            margin: 4px 0
        }

        .sc-goods-list.pic .goods-card.small-pic.card .link {
            border-top: 2px solid #e5e5e5;
            border-right: 2px solid #e5e5e5;
            border-bottom: 2px solid #e5e5e5;
            border-left: 2px solid #e5e5e5;
            margin: 0 4px
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.pic .goods-card.small-pic.card .link {
                border-width: 1px
            }
        }

        .sc-goods-list.pic .goods-card.small-pic.card .photo-block img {
            max-height: 100%
        }

        .sc-goods-list.pic .goods-card.small-pic.card .info {
            min-height: 25px
        }

        .sc-goods-list.pic .goods-card.small-pic.card .info .goods-price {
            margin-top: 5px
        }

        .sc-goods-list.pic .goods-card.small-pic.card .goods-buy.btn2, .sc-goods-list.pic .goods-card.small-pic.card .goods-buy.btn2.ajax-loading {
            bottom: 8px
        }

        .sc-goods-list.pic .goods-card.small-pic.card .goods-buy.btn4 {
            right: 12px;
            bottom: 8px
        }

        .sc-goods-list.pic .goods-card.small-pic.card .goods-buy.btn4.ajax-loading {
            right: 16px;
            bottom: 8px
        }

        .sc-goods-list.pic .photo-block img {
            max-height: 192px;
            max-width: 100%
        }

        .sc-goods-list.pic.waterfall .goods-card.small-pic {
            width: 100%;
            margin: 0
        }

        .sc-goods-list.pic.waterfall .goods-card.small-pic .link {
            border-top: 2px solid #e5e5e5;
            border-right: 2px solid #e5e5e5;
            border-bottom: 2px solid #e5e5e5;
            border-left: 2px solid #e5e5e5;
            margin: 4px
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.pic.waterfall .goods-card.small-pic .link {
                border-width: 1px
            }
        }

        .sc-goods-list.pic.waterfall .goods-card.small-pic .photo-block {
            height: auto;
            min-height: 100px
        }

        .sc-goods-list.pic.waterfall .goods-card.small-pic .photo-block img {
            position: relative;
            height: auto
        }

        .sc-goods-list.pic.waterfall .goods-card.small-pic .info .goods-title {
            height: auto;
            max-height: 32px
        }

        .sc-goods-list.pic.waterfall .goods-card.small-pic .info .goods-price {
            margin-top: 5px;
            margin-bottom: 11px
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion {
            max-height: 220px;
            margin: 0
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .link {
            border-top: 2px solid #e5e5e5;
            border-right: 2px solid #e5e5e5;
            border-bottom: 2px solid #e5e5e5;
            border-left: 2px solid #e5e5e5;
            margin: 4px;
            position: relative
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.pic .goods-card.small-pic.promotion .link {
                border-width: 1px
            }
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .info {
            height: 40px;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding-top: 4px;
            margin-top: 0;
            background-color: #fff
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .info .goods-price {
            font-size: 16px;
            line-height: 16px;
            margin-bottom: 3px;
            overflow: hidden;
            white-space: nowrap
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .info .goods-price em {
            color: #ff495b
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .info .goods-price-taobao {
            display: block;
            clear: left;
            text-decoration: line-through;
            overflow: hidden;
            white-space: nowrap;
            line-height: 12px
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .buy-response, .sc-goods-list.pic .goods-card.small-pic.promotion .goods-buy {
            right: 0;
            bottom: 0;
            width: 47px;
            height: 40px;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 7px 10px;
            color: #fff;
            font-size: 13px;
            line-height: 13px
        }

        .sc-goods-list.pic .goods-card.small-pic.promotion .goods-buy {
            background: #ff495b
        }

        .sc-goods-list.pic.size-2 .goods-card.big-pic.card {
            margin: 4px 0
        }

        .sc-goods-list.size-2 .goods-card.big-pic {
            float: left
        }

        .sc-goods-list.pic.size-4 {
            padding: 5px 15px 0;
            background-color: #fff
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic {
            border-bottom: 2px solid #e5e5e5
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.pic.size-4 .goods-card.multi-pic {
                border-bottom-width: 1px
            }
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic:last-child, .sc-goods-list.pic.size-4 .goods-card.multi-pic:last-of-type {
            margin-bottom: 0;
            border-bottom: none;
            -webkit-border-image: none;
            -moz-border-image: none;
            border-image: none
        }

        .sc-goods-list.list .goods-card.card, .sc-goods-list.list .goods-card.normal {
            -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
            -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
            border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .goods-title {
            font-size: 14px;
            font-weight: 700;
            color: #222;
            line-height: 1.6;
            margin-bottom: 2px;
            word-break: break-word
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .sub-goods-title {
            font-size: 12px;
            color: #888;
            line-height: 1.6;
            margin-bottom: 8px;
            word-break: break-word
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .photo-block {
            float: none;
            background-color: #fff;
            margin: 0 0 20px;
            height: auto;
            display: -webkit-box;
            display: -webkit-flex;
            display: -moz-box;
            display: -ms-flexbox;
            display: flex
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .photo-block .photo-block-sep {
            width: 4px
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .photo-block .phote-block-item {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            position: relative;
            -webkit-box-flex: 1;
            -webkit-flex: 1;
            -moz-box-flex: 1;
            -ms-flex: 1;
            flex: 1
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .photo-block .phote-block-item:before {
            content: ' ';
            display: block;
            padding-top: 100%
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .photo-block .phote-block-item img {
            max-width: 100%;
            max-height: 100%
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic.single-pic .photo-block {
            display: block
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic.single-pic .phote-block-item {
            text-align: left
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic.single-pic .phote-block-item:before {
            content: ' ';
            display: none;
            padding-top: 0
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic.single-pic .phote-block-item img {
            position: static;
            max-height: 175px
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .goods-operate {
            position: relative;
            margin: 0 45px 10px 0;
            padding-right: 16px;
            height: 16px
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .goods-operate .goods-price {
            color: #999;
            font-size: 12px
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .goods-operate .goods-share {
            width: 20px;
            height: 16px;
            background-position: 0 0;
            background-repeat: no-repeat;
            background-size: 51px 30px;
            background-image: url(https://b.yzcdn.cn/v2/image/wap/showcase_e270134134.png)
        }

        .sc-goods-list.pic.size-4 .goods-card.multi-pic .goods-buy.btn1 {
            right: 5px;
            bottom: 7px
        }

        .sc-goods-list.list .goods-card .photo-block {
            float: left;
            margin-right: 13px;
            width: 125px;
            height: 125px
        }

        .sc-goods-list.list .goods-card .photo-block img {
            max-width: 125px;
            max-height: 125px
        }

        .sc-goods-list.list .goods-card .info {
            height: 125px
        }

        .sc-goods-list.list .goods-card .info .goods-title {
            font-size: 14px;
            max-height: 52px;
            margin-bottom: 12px
        }

        .sc-goods-list.list .goods-card .info .goods-price {
            font-size: 15px;
            margin-bottom: 8px
        }

        .sc-goods-list.list .goods-card .info .goods-wish {
            left: 88px;
            right: auto
        }

        .sc-goods-list.list .goods-card .info .wish-response {
            left: 76px;
            right: auto
        }

        .sc-goods-list.list .goods-card .goods-buy.btn1 {
            bottom: 2px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn1.ajax-loading {
            bottom: 6.5px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn2 {
            bottom: 3px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn2.ajax-loading {
            bottom: 5.5px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn3 {
            bottom: 0;
            right: -7px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn3.ajax-loading {
            bottom: 3px;
            right: 10px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn4 {
            bottom: 3px
        }

        .sc-goods-list.list .goods-card .goods-buy.btn4.ajax-loading {
            bottom: 6.5px
        }

        .sc-goods-list.list .goods-card .sold-out {
            background-size: auto 50%
        }

        .sc-goods-list.list .goods-card.normal {
            border-bottom: 2px solid #e5e5e5;
            padding: 10px 0
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.pic.size-4 .goods-card.multi-pic .goods-operate .goods-share {
                background-image: url(https://b.yzcdn.cn/v2/image/wap/showcase@2x_e270134134.png)
            }

            .sc-goods-list.list .goods-card.normal {
                border-bottom-width: 1px
            }
        }

        .sc-goods-list.list .goods-card.normal .link {
            background: 0 0
        }

        .sc-goods-list.list .goods-card.normal .goods-title {
            padding-top: 2px
        }

        .sc-goods-list.list .goods-card.card {
            padding: 5px 0 5px 5px;
            margin: 8px;
            border-top: 2px solid #e5e5e5;
            border-right: 2px solid #e5e5e5;
            border-bottom: 2px solid #e5e5e5;
            border-left: 2px solid #e5e5e5;
            background: #fff
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
            .sc-goods-list.list .goods-card.card {
                border-width: 1px
            }
        }

        .sc-goods-list.list .goods-card.card .goods-title {
            padding: 5px 6px 0 0
        }

        .empty-list {
            font-size: 14px;
            display: block;
            text-align: center;
            padding: 30px 10px;
            color: #999
        }

        .empty-list h4 {
            font-size: 16px;
            margin-bottom: 10px;
            color: #666
        }

        .empty-list div {
            margin-bottom: 20px
        }

        .empty-list .empty-list-content, .empty-list .empty-list-header {
            margin-bottom: 0
        }

        .empty-list .empty-list-content {
            margin-top: 20px
        }

        .empty-list .empty-list-content .home-page {
            padding: 8px 30px
        }

        .sc-goods-list .text-center {
            line-height: 1.5
        }                    </style>

    <link rel="stylesheet" href="/statics/home/showcase_admin_cbae433152bd29e702b2275058204f04.css"
          onerror="_cdnFallback(this)" media="screen">
    <style>
        .content {
            background-color: #f9f9f9;
        }
    </style>
    <script>
        var _global = {
            "kdt_id": 17984819,
            "user_id": 254587,
            "run_mode": "online",
            "debug": false,
            "project": "default",
            "online_debug": false,
            "js": {
                "js_compress": true,
                "css_compress": true,
                "use_js_cdn": true,
                "use_css_cdn": true,
                "message_report": true,
                "checkbrowser": true,
                "hide_wx_nav": true,
                "qn_public": "kdt_img",
                "qn_private": "kdt-private"
            },
            "query_path": "\/showcase\/homepage",
            "query_key": "kdt_id=17984819&reft=1471334504040_1471334509271&spm=f44487562_fake17984819",
            "real_query_path": "get:\/showcase\/homepage\/index.html",
            "module": "showcase",
            "controller": "Showcase_Homepage_Controller",
            "action": "index",
            "full_action": "getIndexHtml",
            "method": "get",
            "format": "html",
            "offline_id": 0,
            "platform": "unknown",
            "is_mobile": false,
            "authorize": "unknown",
            "platform_version": "unknown",
            "mobile_system": "unknown",
            "youzan_app_login": false,
            "page_size": 320,
            "isShopDomain": false,
            "share": {
                "title": "\u53d1\u73b0\u4e00\u5bb6\u597d\u5e97\uff1akankan1",
                "desc": "kankan1",
                "cover": "https:\/\/img.yzcdn.cn\/public_files\/2016\/05\/13\/8f9c442de8666f82abaf7dd71574e997.png"
            },
            "jsBradgeSupport": true,
            "wuxi1_0_0": false,
            "source": "",
            "track": "",
            "nobody": "kd31i767f370us6u9hlp3k1gg4",
            "mp_changed": false,
            "team_certificate": false,
            "is_secured_transactions": 1,
            "hide_shopping_cart": 0,
            "hide_top_bar": 0,
            "mp_data": {
                "logo": "https:\/\/img.yzcdn.cn\/public_files\/2016\/05\/13\/8f9c442de8666f82abaf7dd71574e997.png",
                "team_name": "kankan1",
                "intro": "",
                "is_display_footbar": "1",
                "is_display_suffix_name": "0",
                "suffix_name": "",
                "shopping_cart_style": "1",
                "team_type": "youzan",
                "mobile": "",
                "team_physical": 0
            },
            "wxpay_big": false,
            "alipay_env": true,
            "wxpay_env": false,
            "wxaddress_env": false,
            "is_owner_team": true,
            "weixin_jssdk_use": 1,
            "isWishOpen": 0,
            "wishUrl": "https:\/\/trade.koudaitong.com\/wxpay\/wish?kdt_id=17984819",
            "directSeller": {"is_display_directseller_button": false},
            "fans_id": 0,
            "is_fans": 2,
            "fans_nickname": "",
            "fans_type": 0,
            "fans_token": "",
            "mp_id": 0,
            "fans_picture": "",
            "youzan_fans_id": 0,
            "youzan_fans_nickname": "",
            "youzan_fans_picture": "",
            "youzan_user_id": 254587,
            "no_user_login": false,
            "buyer_id": 254587,
            "change_password_url": "",
            "buyer": {
                "id": 254587,
                "nick_name": "18857107097",
                "avatar": "https:\/\/dn-kdt-img.qbox.me\/upload_files\/avatar.png",
                "phone": "18857107097",
                "gender": 0
            },
            "open_token": [],
            "showcase_type": "homepage",
            "have_goods": true,
            "homepage_template": "shop",
            "spm": {"logType": "f", "logId": 44487562},
            "alias": "a7tdi8lh",
            "ajaxURL": [],
            "no_sidebar": 0,
            "url": {
                "base": "\/\/koudaitong.com",
                "bbs": "http:\/\/bbs.youzan.com",
                "cdn": "\/\/b.yzcdn.cn",
                "cdn_static": "https:\/\/b.yzcdn.cn\/v2",
                "cp": "http:\/\/cp.koudaitong.com",
                "daxue": "http:\/\/xuetang.youzan.com",
                "fenxiao": "\/\/fx.youzan.com",
                "fuwu": "\/\/fuwu.youzan.com",
                "img": "\/\/img.koudaitong.com",
                "imgqn": "https:\/\/img.yzcdn.cn",
                "login": "\/\/login.youzan.com",
                "open": "\/\/open.koudaitong.com",
                "static": "https:\/\/static.koudaitong.com\/v2",
                "trade": "https:\/\/trade.koudaitong.com",
                "v1": "http:\/\/koudaitong.com\/v1",
                "v1_static": "\/\/static.koudaitong.com\/v1",
                "v2": "\/\/koudaitong.com\/v2",
                "wap": "https:\/\/wap.koudaitong.com\/v2",
                "ws": "ws:\/\/s.im.youzan.com:83",
                "www": "\/\/koudaitong.com\/v2",
                "youzan": "\/\/youzan.com",
                "cloud": "http:\/\/dl.koudaitong.com",
                "pf": "https:\/\/pf.koudaitong.com",
                "uic": "https:\/\/uic.koudaitong.com",
                "materials": "\/\/materials.koudaitong.com"
            }
        };</script>
    <script charset="utf-8" src="/statics/home/common_jquery_75554d22a0.js"></script>
    <script charset="utf-8" src="/statics/home/base_2cdff17c2b.js"></script>
    <script charset="utf-8" src="/statics/home/base_5cb9812d2c.js"></script>
    <script charset="utf-8" src="/statics/home/global_icon_929caefc21.js"></script>
    <script charset="utf-8" async="" src="/statics/home/hm.js"></script>
    <script charset="utf-8" src="/statics/home/u_b.js"></script>
    <script charset="utf-8" src="/statics/home/sku_70722b246a.js"></script>
    <script charset="utf-8" src="/statics/home/goods_list_0b03120faf.js"></script>
    <link rel="stylesheet" href="/statics/home/shopnav_custom_1773d863bdd16769f4e92b24d76e1a0a.css">
    <style type="text/css">@charset "UTF-8";
    .block, .block-item {
        display: block;
        overflow: hidden;
        border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch
    }

    .block, .block p, .block-item {
        overflow: hidden
    }

    .quantity, .quantity .txt, .quantity button, .sku-layout .vertical-middle {
        vertical-align: middle
    }

    .block, .block-item, .sku-layout .block-item, .sku-layout-title, .sku-message dl {
        border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch
    }

    .block-item {
        position: relative;
        padding: 10px;
        line-height: 1.5;
        border: 0;
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        border-top: 2px solid #e5e5e5
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .block-item {
            border-top-width: 1px
        }
    }

    .block-item .btn-auth-code {
        position: absolute;
        top: 6px;
        right: 0;
        height: 30px;
        line-height: 30px;
        padding-left: 7px;
        padding-right: 7px;
        font-weight: 700
    }

    .block-item .verify-image {
        position: absolute;
        width: 80px;
        height: 30px;
        right: 10px;
        top: 7px
    }

    .block, .quantity, .quantity .minus {
        position: relative
    }

    .block-item.border-none {
        border-top: 0
    }

    .block-item ul {
        padding-right: 50px
    }

    .block-item ul em {
        color: #999
    }

    .block-item h4.block-item-title {
        line-height: 22px;
        float: left;
        margin-right: 10px
    }

    .block {
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        border-top: 2px solid #e5e5e5;
        border-bottom: 2px solid #e5e5e5;
        margin: 10px 0;
        background-color: #fff;
        font-size: 14px
    }

    .block.top-0, .block:first-child {
        margin-top: 0
    }

    .quantity, .tag {
        display: inline-block
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .block {
            border-top-width: 1px;
            border-bottom-width: 1px
        }
    }

    .block.border-0, .block.border-top-0 {
        border-top: 0
    }

    .block.border-0, .block.border-bottom-0 {
        border-bottom: 0
    }

    .block.bottom-0 {
        margin-bottom: 0
    }

    .block .bottom {
        padding: 10px;
        height: 18px;
        line-height: 18px
    }

    .block .bottom .price {
        float: right;
        color: #f60
    }

    .block.block-list {
        margin: 0;
        padding: 0 0 0 10px;
        list-style: none;
        font-size: 14px;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box
    }

    .block.block-list.margin-top-normal {
        margin-top: 20px
    }

    .block-list.wf {
        padding-left: 0
    }

    .block-list.wf .b-list {
        padding-left: 10px;
        background-color: #fff
    }

    .block-list > .block-item {
        padding: 10px 10px 10px 0
    }

    .block-list > .block-item:first-child {
        border-top: 0 none
    }

    .block.block-list + .block.block-list {
        margin-top: 12px
    }

    .quantity .txt, .quantity button, .tag {
        margin: 0;
        text-align: center
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (-o-min-device-pixel-ratio: 3/2), only screen and (min-device-pixel-ratio: 1.5) {
        .block.block-list li span.clear {
            background: url(https://b.yzcdn.cn/v2/image/wap/icon_clear@2x.png) center center no-repeat;
            background-size: 100%
        }
    }

    .tag {
        background-color: transparent;
        border: 1px solid #e5e5e5;
        border-radius: 3px;
        color: #999;
        font-size: 12px;
        line-height: 12px;
        padding: 4px
    }

    .tag-big {
        font-size: 14px;
        line-height: 18px
    }

    .tag.tag-green {
        color: #06bf04;
        border-color: #0c3
    }

    .tag.tag-orange, .tag.tag-orangef60 {
        color: #f60;
        border-color: #f60
    }

    .tag.tag-white {
        color: #333;
        border-color: #bbb
    }

    .tag.tag-blue {
        color: #38f;
        border-color: #38f
    }

    .tag.tag-red {
        color: #ed5050;
        border-color: #ed5050
    }

    .tag.tag-pink {
        color: #ee614b;
        border-color: #ee614b
    }

    .tag.disabled {
        background-color: #ddd !important;
        background-image: none !important;
        border: 1px solid transparent !important;
        color: #fff !important
    }

    .quantity {
        font-size: 0
    }

    .quantity input[type=number]::-webkit-outer-spin-button {
        margin: 0
    }

    .quantity button {
        border: 2px solid #eee;
        font-size: 16px;
        line-height: 10px;
        font-weight: 700;
        color: #666;
        padding: 5px;
        outline: 0 !important;
        width: 26px;
        height: 30px;
        text-indent: -9999px;
        overflow: hidden
    }

    .quantity .txt {
        font-size: 14px;
        width: 24px;
        height: 18px;
        -webkit-tap-highlight-color: transparent;
        border-radius: 0
    }

    .quantity .minus::before, .quantity .plus::before {
        width: 8px;
        height: 2px;
        top: 0;
        left: 0;
        right: 0;
        margin: auto;
        background-color: #6c6c6c;
        bottom: 0;
        content: ''
    }

    .quantity .txt:focus {
        border-color: #eee
    }

    .quantity .minus {
        border-radius: 4px 0 0 4px;
        border-right: 0 none
    }

    .quantity .minus::before {
        position: absolute
    }

    .quantity .plus {
        position: relative;
        border-left: 0 none;
        border-radius: 0 4px 4px 0
    }

    .quantity .plus::before {
        position: absolute
    }

    .quantity .plus::after {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        margin: auto;
        content: '';
        width: 2px;
        height: 8px;
        background-color: #6c6c6c
    }

    .quantity .minus.disabled::before, .quantity .plus.disabled::after, .quantity .plus.disabled::before {
        background-color: #ddd
    }

    .quantity .response-area {
        width: 42px;
        height: 42px;
        top: -7px;
        position: absolute
    }

    .name-card, .name-card .thumb {
        position: relative;
        overflow: hidden
    }

    .quantity .response-area-plus {
        right: -5px
    }

    .quantity .response-area-minus {
        left: -5px
    }

    .name-card {
        margin-left: 0;
        width: auto;
        padding: 5px 0
    }

    .name-card .thumb {
        width: 60px;
        height: 60px;
        float: left;
        margin-left: auto;
        margin-right: auto;
        background-size: cover
    }

    .name-card .thumb img {
        position: absolute;
        margin: auto;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%
    }

    .name-card a:active, .name-card a:hover {
        text-decoration: none
    }

    .name-card .detail {
        margin-left: 68px;
        width: auto;
        position: relative
    }

    .name-card .detail h3 {
        margin-top: 1px;
        color: #333;
        font-size: 12px;
        line-height: 16px;
        width: 100%
    }

    .name-card .detail p {
        position: relative;
        font-size: 12px;
        line-height: 16px;
        white-space: nowrap;
        margin: 0 0 2px;
        color: #ccc
    }

    .name-card .detail a {
        display: block
    }

    .name-card .detail .l2-ellipsis {
        max-height: 34px;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical
    }

    .name-card .btn-goods-link {
        bottom: 8px;
        right: 10px;
        position: absolute;
        line-height: 14px;
        font-size: 12px
    }

    .name-card.name-card-3col {
        padding: 8px 85px 8px 0
    }

    .name-card.name-card-3col .right-col {
        position: absolute;
        right: 0;
        top: 8px;
        width: 78px;
        padding-right: 10px;
        font-size: 12px
    }

    .name-card.name-card-3col .right-col .price {
        font-size: 14px;
        color: #515151;
        text-align: right;
        line-height: 16px
    }

    .name-card.name-card-3col .right-col .num {
        font-weight: 200;
        text-align: right;
        margin-top: 3px;
        padding: 0;
        color: #555
    }

    .name-card.name-card-3col .right-col .num .num-txt {
        padding: 0;
        line-height: 22px;
        color: #515151
    }

    .name-card.name-card-3col .right-col .order-state {
        font-size: 13px;
        text-align: right
    }

    .sku-layout {
        background-color: #fff
    }

    .sku-layout .line-through {
        display: inline-block;
        text-decoration: line-through;
        line-height: 23px
    }

    .sku-layout .block-item {
        border: 0;
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        border-top: 2px solid #e5e5e5
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .sku-layout .block-item {
            border-top-width: 1px
        }
    }

    .sku-layout .block-item:first-child {
        border-top: 0 none
    }

    .sku-box-shadow {
        -webkit-box-shadow: 0 -1px 14px rgba(0, 0, 0, .9);
        box-shadow: 0 -1px 14px rgba(0, 0, 0, .9)
    }

    .sku-layout-title {
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        border-bottom: 2px solid #e5e5e5;
        border-top-width: 0;
        position: static;
        padding: 10px 0;
        margin-left: 10px
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .sku-layout-title {
            border-bottom-width: 1px
        }
    }

    .sku-layout-title .thumb {
        width: 50px;
        height: 50px;
        border: 1px solid #eee;
        border-radius: 2px
    }

    .sku-layout-title .goods-base-info {
        margin-left: 60px
    }

    .sku-layout-title .goods-base-info .title {
        padding-right: 52px;
        margin-bottom: 5px;
        font-size: 14px;
        line-height: 22px
    }

    .sku-layout-title .goods-base-info .goods-price {
        padding: 0 55px 0 0
    }

    .sku-layout-title .goods-base-info .current-price {
        line-height: 20px
    }

    .sku-layout-title .goods-base-info .current-price .price-name {
        padding-top: 1px
    }

    .sku-layout-title .goods-base-info .current-price .price-tag {
        padding: 0 5px;
        margin-left: 5px;
        line-height: 16px;
        font-size: 10px;
        border-radius: 2px
    }

    .sku-layout-title .goods-base-info .old-price, .sku-layout-title .goods-base-info .original-price {
        color: #999
    }

    .sku-layout-title .goods-base-info .original-price {
        display: none
    }

    .sku-layout-title .goods-base-info .old-price {
        display: block;
        margin: 5px 0 0 2px;
        font-size: 10px;
        line-height: 12px
    }

    .sku-layout .sku-cancel {
        position: absolute;
        right: 0;
        top: 0;
        padding: 10px
    }

    .sku-layout .sku-cancel .cancel-img {
        height: 22px;
        width: 22px;
        background-image: url(https://b.yzcdn.cn/v2/image/wap/sku/icon_close.png);
        background-size: 22px 22px
    }

    .sku-layout .goods-models .sku-list-container {
        padding: 10px 0 0;
        border: 0
    }

    .sku-layout .sku-sel-title {
        margin-bottom: 10px;
        font-size: 13px
    }

    .sku-layout .sku-sel-list {
        zoom: 1;
        padding-left: 0;
        margin-bottom: 0
    }

    .sku-layout .sku-sel-list:after {
        content: '';
        display: table;
        clear: both
    }

    .sku-layout .sku-tag {
        position: relative;
        margin-right: 10px;
        min-width: 32px;
        max-width: 180px;
        line-height: 16px;
        padding: 5px 9px;
        margin-bottom: 10px;
        color: #333;
        border-color: #999
    }

    .sku-layout .sku-tag.unavailable {
        border-color: #e5e5e5;
        color: #cacaca;
        background-color: #eee;
        cursor: not-allowed
    }

    .sku-layout .sku-tag.active {
        color: #fff;
        background-color: #f60;
        border-color: #f60
    }

    .sku-layout .sku-num {
        line-height: 29px
    }

    .sku-layout .other-info {
        line-height: 14px
    }

    .sku-layout .stock {
        display: inline-block;
        margin-right: 10px;
        font-size: 12px
    }

    .sku-layout .quota {
        display: inline-block;
        font-size: 12px;
        color: #f15a0c
    }

    .sku-layout .quantity {
        float: right
    }

    .sku-layout .quantity .minus {
        border-radius: 2px 0 0 2px
    }

    .sku-layout .quantity .minus.disabled {
        background-color: #f8f8f8;
        border-color: #e8e8e8 #999 #e8e8e8 #e8e8e8
    }

    .sku-layout .quantity .minus.disabled::before {
        background-color: #bbb
    }

    .sku-layout .quantity .plus {
        border-radius: 0 2px 2px 0
    }

    .sku-layout .quantity .plus.disabled {
        background-color: #f8f8f8;
        border-color: #e8e8e8 #e8e8e8 #e8e8e8 #999
    }

    .sku-layout .quantity .plus.disabled::after, .sku-layout .quantity .plus.disabled::before {
        background-color: #bbb
    }

    .sku-layout .quantity .txt {
        width: 33px;
        height: 25px;
        padding: 1px;
        border: 1px solid #999;
        border-width: 1px 0;
        -webkit-box-sizing: content-box;
        -moz-box-sizing: content-box;
        box-sizing: content-box;
        color: #666
    }

    .sku-layout .quantity .txtCover {
        position: absolute;
        top: 0;
        left: 37px;
        bottom: 0;
        right: 37px
    }

    .sku-layout .quantity .minus, .sku-layout .quantity .plus {
        width: 37px;
        height: 29px;
        background-color: #fff;
        border: 1px solid #999
    }

    .sku-layout .quantity .minus::before, .sku-layout .quantity .plus::before {
        height: 1px;
        width: 9px;
        background-color: #666
    }

    .sku-layout .quantity .minus::after, .sku-layout .quantity .plus::after {
        width: 1px;
        height: 9px;
        background-color: #666
    }

    sup.required {
        color: red !important
    }

    .block-list .block-item.block-item-messages {
        padding: 0;
        overflow: visible;
        -webkit-tap-highlight-color: transparent
    }

    .sku-message dl {
        -webkit-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        -moz-border-image: url(https://b.yzcdn.cn/v2/image/wap/border-line-2.png) 2 stretch;
        border-top: 2px solid #e5e5e5
    }

    @media only screen and (-webkit-min-device-pixel-ratio: 1.5), only screen and (min--moz-device-pixel-ratio: 1.5), only screen and (min-device-pixel-ratio: 1.5) {
        .sku-message dl {
            border-top-width: 1px
        }
    }

    .sku-message dl:first-child {
        border: 0
    }

    .sku-message dt {
        width: 70px;
        position: relative
    }

    .sku-message dt .required {
        position: absolute;
        left: -7px;
        font-size: 14px
    }

    .sku-message dt label {
        line-height: 40px
    }

    .sku-message .comment-wrapper {
        margin-left: 90px;
        padding-right: 5px;
        position: relative
    }

    .sku-message .comment-wrapper .txt, .sku-message .comment-wrapper .txta {
        padding: 9px 0 10px;
        line-height: 20px;
        width: 97%;
        border: 0;
        outline: 0;
        -webkit-appearance: none;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box
    }

    .sku-message .comment-wrapper .txt[type=text] {
        padding: 10px 0
    }

    .sku-message .comment-wrapper .txta {
        padding: 0;
        margin-top: 10px;
        height: 50px;
        resize: none
    }

    .image-input-trigger {
        padding: 0 10px;
        height: 24px;
        line-height: 24px;
        margin: 8px 0;
        -webkit-tap-highlight-color: transparent
    }

    .image-input-trigger .select-photo::before, .image-input-trigger .take-photo::before {
        content: '';
        position: relative;
        display: inline-block;
        margin-right: 5px;
        width: 14px;
        height: 12px;
        background-size: 14px 12px
    }

    .image-input-trigger .take-photo {
        margin-right: 10px
    }

    .image-input-trigger .take-photo::before {
        top: 1px;
        background-image: url(https://b.yzcdn.cn/v2/image/wap/sku/icon_camera.png)
    }

    .image-input-trigger .select-photo::before {
        top: 2px;
        margin-left: 10px;
        background-image: url(https://b.yzcdn.cn/v2/image/wap/sku/icon_img.png)
    }

    .image-input-show {
        margin-bottom: 8px;
        height: 60px
    }

    .image-input-show img {
        margin-right: 5px;
        width: 60px;
        height: 60px;
        vertical-align: text-bottom
    }

    .image-input-show .img-tip {
        font-size: 10px;
        color: #999
    }

    .photo-input {
        position: absolute;
        opacity: 0;
        height: 40px;
        width: 175px;
        left: 0;
        top: 0
    }

    .sku-layout {
        -webkit-overflow-scrolling: touch
    }

    .sku-layout .layout-content {
        overflow-y: scroll;
        border: 1px solid #fff;
        line-height: 20px;
        background-color: #fff
    }

    .sku-layout .content-foot {
        padding: 10px;
        font-size: 0
    }

    .sku-layout .content-foot .half-button {
        float: left;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        width: 50%
    }

    .sku-layout .content-foot .half-button:first-child {
        padding-right: 5px
    }

    .sku-layout .content-foot .half-button:last-child {
        padding-left: 5px
    }

    .sku-layout .content-foot .btn-buy, .sku-layout .content-foot .btn-cart, .sku-layout .content-foot .btn-next {
        padding: 8px;
        line-height: 17px;
        font-size: 14px
    }</style>
</head>
<body class="body-fixed-bottom" style="min-height: 773px;">

<div class="container " style="min-height: 599px;">
    <div class="content ">
        <div class="content-body js-page-content">
            <!-- 店铺主页模板-默认 -->
            <div class="tpl-shop">
                <div class="tpl-shop-header"
                     style="background-color:#ffffff; background-image: url(https://img.yzcdn.cn/upload_files/2015/01/22/Fv-GeYTN-o5_lm1QS4G4byXoHGUK.png);">
                    <div class="tpl-shop-title">黑眼圈365</div>
                    <div class="tpl-shop-avatar">
                        <img src="/statics/home/8f9c442de8666f82abaf7dd71574e997.png" alt="头像">
                    </div>
                </div>
                <div class="tpl-shop-content">
                    <ul class="clearfix">
                        <li class="js-all-goods">
                            <a href="https://wap.koudaitong.com/v2/allgoods/17984819">
                                <span class="count"><%=goodsList.size()%></span>
                                <span class="text">全部商品</span>
                            </a>
                        </li>
                        <li class="js-new-goods">
                            <a href="https://wap.koudaitong.com/v2/allgoods/17984819?t=1">
                                <span class="count">0</span>
                                <span class="text">上新商品</span>
                            </a>
                        </li>
                        <li class="js-order">
                            <a href="https://wap.koudaitong.com/v2/usercenter/jmb4ypal">
                                <span class="count user"></span>
                                <span class="text">会员中心</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- 商品搜索 -->
            <div class="custom-search">
                <form action="https://wap.koudaitong.com/v2/search" method="GET">
                    <input type="search" class="custom-search-input" placeholder="商品搜索：请输入商品关键字" name="q" value="">
                    <input type="hidden" name="kdt_id" value="17984819">
                    <button type="submit" class="custom-search-button">搜索</button>
                </form>
            </div>

            <ul class="custom-nav clearfix">
                <li>
                    <a><span class="custom-nav-title">全部商品</span></a>
                </li>
            </ul>
            <ul class="js-goods-list sc-goods-list pic clearfix size-1 " data-size="1" data-showtype="promotion"
                style="visibility: visible;">
                <%
                    for (int i = 0; goodsList != null && i < goodsList.size(); i++) {
                        Goods goods = goodsList.get(i);
                %>
                <li class="js-goods-card goods-card small-pic promotion">
                    <a href="/goods/get?id=<%=goods.getId()%>" class="js-goods link clearfix"
                       target="_blank">
                        <div class="photo-block" data-width="640" data-height="640"
                             style="background-color: rgb(255, 255, 255);">
                            <img class="goods-photo js-goods-lazy"
                                 data-src="https://img.yzcdn.cn/upload_files/2015/05/14/Fh1ZR74CpUm0s85svgQuU-MQ3oQd.png?imageView2/2/w/280/h/280/q/75/format/webp"
                                 src="/statics/home/Fh1ZR74CpUm0s85svgQuU-MQ3oQd.png" style="display: block;">
                        </div>
                        <div class="info clearfix info-no-title info-price btn4">
                            <p class="goods-title"><%=goods.getTitle() %></p>
                            <p class="goods-sub-title c-black hide"></p>
                            <p class="goods-price">
                                <em><%=goods.getPrice() %>积分</em>
                            </p>
                            <p class="goods-price-taobao  hide"></p>
                        </div>
                        <div class="goods-buy btn4 info-no-title">
                            我要抢购
                        </div>
                        <div class="js-goods-buy buy-response" data-alias="3eszo1hm1wd1v" data-postage="0"
                             data-buyway="1" data-id="294942850" data-isvirtual="2"></div>
                    </a>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
        <div class="content-sidebar">
            <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=17984819" class="link">
                <div class="sidebar-section shop-card">
                    <div class="table-cell">
                        <img src="/statics/home/8f9c442de8666f82abaf7dd71574e997(1).png" width="60" height="60"
                             class="shop-img">
                    </div>
                    <div class="table-cell">
                        <p class="shop-name">黑眼圈365</p>
                    </div>
                </div>
            </a>
        </div>
        <script>
            var showQcode = function () {
                if (typeof $ !== 'undefined') {
                    var $qrcode = $('.js-follow-qrcode');
                    $qrcode.removeClass('js-follow-qrcode'); //兼容图文
                    if ($qrcode.length == 0) return;
                    if (!window._global.mp_data || $qrcode.length <= 0 || ( window._global.is_mobile && !(/ipad/gi).test(navigator.userAgent.toLowerCase()))) {
                        return false;
                    }
                    var followQrcodeSrc = 'https://open.weixin.qq.com/qr/code/?username=' + window._global.mp_data.mp_weixin;
                    var img = new Image();
                    img.width = 158;
                    img.height = 158;
                    $(img).on('load', function (event) {
                        $qrcode.append(img).removeClass('loading');
                    });
                    img.src = followQrcodeSrc;
                } else {
                    setTimeout(showQcode, 500);
                }
            };
            showQcode();
        </script>
        <div id="shop-nav" style="display: block;">
            <div class="js-navmenu js-footer-auto-ele shop-nav nav-menu nav-menu-1 has-menu-3">
                <div class="nav-special-item">
                    <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=17984819" class="home">主页</a>
                </div>
                <div class="nav-items-wrap">
                    <div class="nav-item">
                        <a class="mainmenu js-mainmenu" href="https://wap.koudaitong.com/v2/allgoods/17984819">
                            <span class="mainmenu-txt">全部商品</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a class="mainmenu js-mainmenu" href="https://wap.koudaitong.com/v2/cart/17984819">
                            <span class="mainmenu-txt">赚积分</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a class="mainmenu js-mainmenu" href="https://wap.koudaitong.com/v2/usercenter/jmb4ypal">
                            <span class="mainmenu-txt">会员中心</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="js-footer" style="min-height: 1px;">
    <div>
        <div class="footer">
            <div class="copyright">
                <div class="ft-links">
                    <a href="https://wap.koudaitong.com/v2/showcase/homepage?kdt_id=17984819" target="_blank">店铺主页</a>
                    <a href="https://wap.koudaitong.com/v2/showcase/usercenter?kdt_id=17984819" target="_blank">会员中心</a>
                </div>
                <div class="ft-copyright">
                    <a href="https://youzan.com/" target="_blank">黑眼圈365</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>