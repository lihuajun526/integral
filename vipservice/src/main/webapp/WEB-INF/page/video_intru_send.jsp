<%@ page import="com.operational.platform.common.util.Config" %>
<%@ page import="com.operational.platform.dbservice.model.VideoGood" %>
<%@ page import="com.operational.platform.dbservice.model.DbVideoPerson" %>
<%@ page import="com.operational.platform.dbservice.model.DbShortComment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appPath = Config.get("app.name");
    VideoGood videoGood = (VideoGood) request.getAttribute("videoGood");
    String score = videoGood.getScore();
    score = score.substring(score.indexOf("<strong>"), score.indexOf("</strong>")).replaceAll("<strong>", "").trim();
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
</head>
<body>
<section class="article135">
    <section class="_135editor" style="border: 0px none; padding: 0px;">
        <section label="Powered by 135editor.com" style="font-family:微软雅黑;font-size:16px;">
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; position: relative; box-sizing: border-box; transform: rotateZ(0deg); margin: 0px auto; width: 100%;"
                     data-color="#272724" data-custom="#272724" data-width="100%">
                <section
                        style="margin: 0.5em 0px 0px; padding: 0px; max-width: 100%; box-sizing: border-box; min-width: 0px; color: rgb(62, 62, 62); font-size: 15px; line-height: 24px; border: none; word-wrap: break-word !important; ">
                    <section>
                        <section
                                style="margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; display: inline-block; vertical-align: top; height: 1.8em; width: 1.8em; border-radius: 50%; border: 3px solid rgb(39, 39, 36); font-size: 1.6em; font-family: inherit; font-weight: inherit; text-align: center; text-decoration: inherit; color: rgb(39, 39, 36); background: rgb(254, 254, 254); word-wrap: break-word !important;">
                            <section class="autonum"
                                     style="margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; line-height: 1.6em; word-wrap: break-word !important;"
                                     data-original-title="" title="">
                                <span style="font-size: 20px;"><%=score%></span>
                            </section>
                        </section>
                    </section>
                    <section
                            style="margin: -1.56em 0px 1em 0.5em; padding: 0px; float: left; border-radius: 1em; font-size: 1.2em; font-family: inherit; font-weight: inherit; text-decoration: inherit; color: rgb(254, 254, 254); border-color: rgb(39, 39, 36); background-color: rgb(39, 39, 36); z-index: -1; word-wrap: break-word !important; box-sizing: border-box;">
                        <span style="box-sizing: border-box; display: inline-block; float: left; line-height: 1.6em; margin: 0px 5px; max-width: 100%; padding: 0px 10px 0px 2em; vertical-align: top; font-family: 微软雅黑; font-size: 15px; word-wrap: break-word !important;"
                              class="135brush" data-brushtype="text"><%=videoGood.getTitle()%></span>
                    </section>
                    <section style="clear:both;width:0px;height:0px"></section>
                </section>
            </section>
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; position: relative; box-sizing: border-box;">
                <section style="margin:20px auto;">
                    <section style="border: 2px solid rgb(215, 215, 215); box-sizing: border-box;">
                        <section class="layout"
                                 style="width: 32%; float: left; padding-left: 15px; margin-bottom: 10px; margin-top: -20px; box-sizing: border-box;"
                                 data-width="32%">
                            <img style="display: block; box-shadow: rgb(51, 51, 51) 0px 0px 10px; width: 100%;"
                                 src="<%=videoGood.getImage()%>"
                                 data-width="100%" title="timg (2).jpg" alt="timg (2).jpg"/>
                        </section>
                        <section class="layout"
                                 style="width: 68%; float: left; padding: 0px 15px; box-sizing: border-box;"
                                 data-width="68%">
                            <p class="135brush" data-brushtype="text"
                               style="font-size: 16px; margin: 10px 0px; line-height: 1.8em; white-space: normal;">
                                《<%=videoGood.getTitle()%>》</p>
                            <section class="135brush">
                                <h1 class=""
                                    style="max-width: 100%; color: rgb(62, 62, 62); white-space: normal;">
                                    <span style="max-width: 100%; letter-spacing: 1px; font-family: 微软雅黑; font-size: 15px;"><%=videoGood.getMeta().length() > 26 ? videoGood.getMeta().substring(0, 26) : videoGood.getMeta()%></span>
                                </h1>
                            </section>
                        </section>
                        <p style="padding:10px;">
                            <span style="font-family: 微软雅黑; font-size: 12px; line-height: 19.2px; text-align: justify; text-indent: 32px; white-space: pre-wrap;"><%=videoGood.getDescription()%></span>
                        </p>
                        <section class="_135editor" style="border: 0px none; padding: 0px;">
                            <section style="text-align:center;">
                                <iframe class="video_iframe"
                                        style="position: relative;z-index:1;height:240px;width:320px;" scrolling="no"
                                        src="https://v.qq.com/iframe/preview.html?vid=j0025qw0y2g&auto=0"
                                        allowfullscreen="" frameborder="0"></iframe>
                            </section>
                        </section>
                    </section>
                </section>
            </section>
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; box-sizing: border-box;">
                <section data-role="paragraph" class="_135editor"
                         style="border: 0px none; padding: 0px; box-sizing: border-box;">
                    <p style="white-space: normal;">
                        <br/>
                    </p>
                </section>
                <section class="_135editor"
                         style="border: 0px none; padding: 0px; position: relative; box-sizing: border-box;">
                    <section style="width:100%; text-align:center;" data-width="100%">
                        <img style="width:80%;"
                             src="http://rdn.135editor.com/cache/remote/aHR0cHM6Ly9tbWJpei5xbG9nby5jbi9tbWJpel9wbmcvZmdua3hmR25ua1RtbDVReFBZY3BBUlQ1aE1uZzU3TlQ1aWJ5bDNVQTdMdEJtdHZlZTBpY2huUGlhZVI1TExuOVpFTVlLQllIalJRc2lhWGlidG5UQU83NEhGZy8wP3d4X2ZtdD1wbmc="
                             data-width="80%"/>
                    </section>
                </section>
            </section>
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; box-sizing: border-box;">
                <section class="_135editor" style="border: 0px none; padding: 0px; box-sizing: border-box;">
                    <section style="width:100%; text-align:center;" data-width="100%">
                        <section style="display:inline-block;">
                            <section style="float:left;">
                                <img style="display:block;"
                                     src="<%=appPath%>/images/sc1.png"/>
                            </section>
                            <section
                                    style="float: left; font-size: 16px; line-height: 50px; padding: 0px 6px; box-sizing: border-box;"
                                    class="135brush" data-brushtype="text">
                                <span style="font-size: 17px;">导演/演员</span>
                            </section>
                            <section style="float:left; margin-top:14px;">
                                <img style="display:block;" src="<%=appPath%>/images/sc2.png"/>
                            </section>
                        </section>
                    </section>
                </section>
            </section>
            <%
                for (int i = 0; i < 4; i++) {
                    DbVideoPerson videoPerson = videoGood.getVideoPersons().get(i);
            %>
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; position: relative; box-sizing: border-box;"
                     data-color="rgb(33, 33, 34)" data-custom="rgb(33, 33, 34)">
                <section
                        style="margin: 0.5em 0px 0px; padding: 0px; max-width: 100%; box-sizing: border-box; min-width: 0px; color: rgb(62, 62, 62); font-size: 15px; line-height: 24px; border: none; word-wrap: break-word !important; ">
                    <section>
                        <section
                                style="margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; display: inline-block; vertical-align: top; height: 1.8em; width: 1.8em; border-radius: 50%; border: 3px solid rgb(33, 33, 34); font-size: 1.6em; font-family: inherit; font-weight: inherit; text-align: center; text-decoration: inherit; color: rgb(33, 33, 34); background: rgb(254, 254, 254); word-wrap: break-word !important;">
                            <section class="autonum"
                                     style="margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; line-height: 1.6em; word-wrap: break-word !important;"
                                     data-original-title="" title=""><%=i + 1%>
                            </section>
                        </section>
                    </section>
                    <section
                            style="margin: -1.56em 0px 1em 0.5em; padding: 0px; float: left; border-radius: 1em; font-size: 1.2em; font-family: inherit; font-weight: inherit; text-decoration: inherit; color: rgb(254, 254, 254); border-color: rgb(33, 33, 34); background-color: rgb(33, 33, 34); z-index: -1; word-wrap: break-word !important; box-sizing: border-box;">
                        <span style="box-sizing: border-box; display: inline-block; float: left; line-height: 1.6em; margin: 0px 5px; max-width: 100%; padding: 0px 10px 0px 2em; vertical-align: top; font-family: 微软雅黑; font-size: 15px; word-wrap: break-word !important;"
                              class="135brush" data-brushtype="text"><%=videoPerson.getName()%></span>
                    </section>
                    <section style="clear:both;width:0px;height:0px"></section>
                </section>
            </section>
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; position: relative; box-sizing: border-box;"
                     data-color="#212122" data-custom="#212122">
                <section
                        style="display: inline-block; vertical-align: top; width:50%;float:left;padding:0 30px;box-sizing:border-box;"
                        data-width="50%">
                    <section data-role="circle"
                             style="border-radius: 100%; overflow: hidden; margin: 0px auto; width: 100%; box-sizing: border-box; padding-bottom: 100%; height: 0px; background-image: url('<%=videoPerson.getPhoto()%>'); background-position: 50% 50%; background-size: cover;"
                             data-width="100%">
                        <img src="<%=videoPerson.getPhoto()%>" style="opacity:0;"/>
                    </section>
                </section>
                <section
                        style="display: inline-block; vertical-align: top; border-left: 1px solid rgb(33, 33, 34); box-sizing: border-box; padding-bottom: 1em; width: 50%;"
                        data-width="50%">
                    <section
                            style="border: 1px solid; box-sizing: border-box; color: rgb(254, 254, 254); font-size: 18px; width: 0.8em; height: 0.8em; line-height: 0.8em; margin-left: -0.42em; border-radius: 100%; margin-top: 3em; background-color: rgb(33, 33, 34);"></section>
                    <section style="padding-left: 20px;box-sizing:border-box;margin-top:-4em;" class="135brush">
                        <p style="white-space: normal;">
                            <span style="color: #424242; text-align: justify; font-family: 微软雅黑; font-size: 12px;"><%=videoPerson.getPosition()%></span>
                        </p>

                        <p style="white-space: normal;"><br/></p>
                    </section>
                </section>
            </section>
            <p style="white-space: normal;"><br/></p>
            <%
                }
            %>
            <section class="_135editor" style="border: 0px none; padding: 0px;">
                <section style="width:100%; text-align:center;" data-width="100%">
                    <section style="display:inline-block;">
                        <section style="float:left;">
                            <img style="display:block;" src="<%=appPath%>/images/sc1.png"/>
                        </section>
                        <section style="float: left; line-height: 50px; padding-right: 6px; padding-left: 6px;"
                                 class="135brush" data-brushtype="text">
                            <span style="font-size: 17px;">豆瓣影评</span>
                        </section>
                        <section style="float:left; margin-top:14px;">
                            <img style="display:block;" src="<%=appPath%>/images/sc2.png"/>
                        </section>
                    </section>
                </section>
            </section>
            <section data-role="paragraph" class="_135editor" style="border: 0px none; padding: 0px;">
                <p><br/></p>
            </section>
            <%
                for (DbShortComment shortComment : videoGood.getShortComments()) {
            %>
            <section class="_135editor" data-tools="135编辑器" style="border: 0px none; padding: 0px;"
                     data-color="rgb(33, 33, 34)" data-custom="rgb(33, 33, 34)">
                <section
                        style="border:0;padding:0;margin:0 auto;text-align:left;white-space:normal;display:inline-block">
                    <section style="display:inline-block;float:left;vertical-align:top">
                        <section
                                style="width:60px;text-align:center;border-radius:100%;display:inline-block;color:#FFF;vertical-align:top">
                            <img src="<%=shortComment.getPhoto()%>"
                                 style="width: 100%; border: 1px solid rgb(33, 33, 34); margin: 0px; padding: 0px; border-radius: 100%;"
                                 title="1487036877322462.jpg" data-width="100%"/>
                        </section>
                    </section>
                    <section
                            style="padding:0;margin-left:70px;text-align:left;font-size:14px;color:#333;line-height:25px">
                        <p style="padding: 0px; margin: 0px 0px 0px 10px; clear: none; font-size: 14px; display: inline-block; color: rgb(33, 33, 34);">
                            <%=shortComment.getName()%>
                        </p>

                        <p style="padding:0;margin:0 0 0 5px;clear:none;font-size:12px;display:inline-block;color:#999">
                            <%=shortComment.getDateTime()%>
                        </p>
                        <section class="135brush" data-style="clear:none;"
                                 style="padding:0;margin:0 0 0 10px;color:#787878">
                            <%=shortComment.getContent()%>
                        </section>
                    </section>
                    <section style="clear:both;"></section>
                </section>
            </section>
            <section data-role="paragraph" class="_135editor" style="border: 0px none; padding: 0px;">
                <p>
                    <br/>
                </p>
            </section>
            <%
                }
            %>
            <section class="_135editor" data-tools="135编辑器" data-id="91014" style="border: 0px none; padding: 0px;">
                <section style="width:100%;" data-width="100%">
                    <section style="width:100%; text-align:center; overflow:hidden;" data-width="100%">
                        <section style="width:35px; display:inline-block;">
                            <img style="width:100%; vertical-align:middle;"
                                 src="<%=appPath%>/images/sc5.png"
                                 data-width="100%"/>
                        </section>
                    </section>
                    <section style="width:100%; text-align:center;" data-width="100%">
                        <section style="border:solid 2px #c1533d; display:inline-block; padding:3px 3px;">
                            <section style="padding:0px 20px; background-color:#c1533d; line-height:30px; color:#fff;"
                                     class="135brush" data-brushtype="text">
                                关注影咖365，免费看电影
                            </section>
                        </section>
                    </section>
                    <section style="width:100%; text-align:center; overflow:hidden; margin-top:-3px;" data-width="100%">
                        <section style="width:35px; display:inline-block;">
                            <img style="width:100%; vertical-align:middle;" src="<%=appPath%>/images/sc4.png"
                                 data-width="100%"/>
                        </section>
                    </section>
                </section>
            </section>
            <section class="_135editor" data-tools="135编辑器"
                     style="border: 0px none; padding: 0px; box-sizing: border-box;">
                <section class="_135editor" style="border: 0px none; padding: 0px; box-sizing: border-box;">
                    <section style="width:100%;" data-width="100%">
                        <section
                                style="width:300px; height:100px; margin:10px auto; background-image:url(<%=appPath%>/images/sc3.png); background-repeat:no-repeat; background-size:100%; overflow:hidden;">
                            <section style="float:right; width:80px; height:80px; margin-right:10px; margin-top:8px;">
                                <img style="width:100%; display:block; height:100% !important;"
                                     src="http://www.yka365.com/upload/supervip/qrcode_12.jpg" data-width="100%"
                                     data-height="100%"/>
                            </section>
                        </section>
                    </section>
                </section>
            </section>
        </section>
    </section>
    <p>
        <br/>
    </p>
</section>
</body>
</html>