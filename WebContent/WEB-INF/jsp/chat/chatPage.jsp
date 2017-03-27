<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE>
<html>
<link rel="stylesheet" type="text/css" href="${context }/css/chat.css" />
<script type="text/javascript" src="${context }/js/jquery.js"></script>
<script type="text/javascript" src="${context }/js/layer.js"></script>
<script type="text/javascript" src="${context }/js/chat.js"></script>
<script type="text/javascript">
window.alert = layer.alert;
</script>
<body class="keBody">
<h1 class="keTitle">在线课堂聊天室</h1>
<div class="kePublic">
<input type="hidden" id="userId" value="${sessionScope.user.userId }">
<input type="hidden" id="userName" value="${sessionScope.user.realName }">
<input type="hidden" id="courseId" value="${requestScope.courseId }">
<!--效果html开始-->
<div class="content">
    <div class="chatBox">
        <div class="chatLeft">
            <div class="chat01">
                <div class="chat01_title">
                    <ul class="talkTo">
                        <li><a href="javascript:;">${sessionScope.user.realName }</a></li></ul>
                    <a class="close_btn" href="javascript:;"></a>
                </div>
                <div class="chat01_content">
                    <div class="message_box mes" style="display: block;"></div>
                </div>
            </div>
            <div class="chat02">
                <div class="chat02_title">
                    <a class="chat02_title_btn ctb01" href="javascript:;"></a>
                    <c:if test="${sessionScope.user.role==0 }">
	                    <a id="handUp" class="chat02_hand_up" href="javascript:;"></a>
                    </c:if>
                    <!-- <label class="chat02_title_t"><a href="chat.htm" target="_blank">聊天记录</a></label> -->
                    <div class="wl_faces_box">
                        <div class="wl_faces_content">
                            <div class="title">
                                <ul>
                                    <li class="title_name">常用表情</li><li class="wl_faces_close"><span>&nbsp;</span></li></ul>
                            </div>
                            <div class="wl_faces_main">
                                <ul>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_01.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_02.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_03.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_04.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_05.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_06.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_07.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_08.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_09.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_10.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_11.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_12.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_13.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_14.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_15.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_16.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_17.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_18.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_19.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_20.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_21.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_22.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_23.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_24.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_25.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_26.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_27.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_28.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_29.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_30.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_31.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_32.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_33.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_34.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_35.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_36.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_37.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_38.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_39.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_40.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_41.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_42.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_43.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_44.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_45.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_46.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_47.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_48.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_49.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_50.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_51.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_52.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_53.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_54.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_55.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_56.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_57.gif" /></a></li>
                                    <li><a href="javascript:;">
                                        <img src="${context}/img/emo_58.gif" /></a></li><li><a href="javascript:;">
                                            <img src="${context}/img/emo_59.gif" /></a></li><li><a href="javascript:;">
                                                <img src="${context}/img/emo_60.gif" /></a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="wlf_icon"></div> 
                    </div>
                </div>
                <div class="chat02_content">
                    <textarea id="textarea"></textarea>
                </div>
                <div class="chat02_bar">
                    <ul>
                        <li style="right: 5px; top: 5px;"><a href="javascript:;" class="send_msg">
                            <img src="${context}/img/send_btn.jpg" /></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="chatRight">
            <div class="chat03">
                <div class="chat03_title">
                    <label class="chat03_title_t">成员列表</label>
                </div>
                <div id="chatUserPage" class="chat03_content"></div>
            </div>
        </div>
        <div style="clear: both;">
        </div>
    </div>
</div>
<!--效果html结束-->
</div>
</body>
</html>