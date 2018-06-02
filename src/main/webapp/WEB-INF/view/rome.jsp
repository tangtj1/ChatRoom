<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tang
  Date: 1/24/18
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="include/taglib.jsp" %>
<html>
<head>
  <%@include file="include/head.jsp"%>
  <title>Title</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <!-- add jq -->
  <%--<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>--%>
  <%--<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>--%>
  <%--<script src="${pageContext.request.contextPath}/js/mdui.js"></script>--%>
  <%--<script src="${pageContext.request.contextPath}/js/rome.js"></script>--%>
  <!-- add bootstrap -->
  <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">--%>
  <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mdui.css">--%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common/rome.css">
  <script src="${basePath}/static/common/rome.js"></script>
</head>
<body>
<div class="container" style="height: 100%;">
  <div class="col-md-9" style="height: 75%;padding: 0 0 0 0;">
    <div class="infoBlock" style="margin-bottom: 0">
      <span class="h1">${group.name}
        <small>Id:${group.key}</small></span>

      <a href="${pageContext.request.contextPath}/chat/room/${group.key}/exit/" class="btn btn-default mdui-float-right">退出房间</a>
    </div>
    <div class="col-md-12" style="height: 100%;padding: 0 0 0 0">
      <div class="msgBox mdui-shadow-1" style="height: 100%;border-radius: 5px;overflow: auto;">
        <div class="messages">
        </div>
      </div>

    </div>
    <div class="col-md-12" style="padding: 15px 0 0 0">
      <div class="form-inline">
        <div class="input-group col-md-12">
          <input type="text" id="msgInput" class="input-lg col-md-10" style="border: #1b6d85 1px solid"
                 placeholder="输入消息">
          <div class="col-md-2" style="padding: 0 0 0 15px">
            <button class="btn btn-lg btn-primary col-md-12" id="send">发送</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="col-md-3" style="height: 100%">
    <div class="infoBlock text-center">
      <a href="#"><span class="glyphicon glyphicon-user" style="font-size: 25px"
                        aria-hidden="true" id="userName"> ${user.username}</span></a>
    </div>

    <div class="infoBlock" id="romeInfo">
      <div class="mdui-panel" mdui-panel>
        <div class="mdui-panel-item">
          <div class="mdui-panel-item-header">
            在线人数
            <div class="col-md-12 mdui-text-right">
              <span class="badge" id="groupUserInfo"></span>
            </div>
          </div>
          <div class="mdui-panel-item-body">
            <div style="overflow: auto; height: 200px">
              <ul class="mdui-list mdui-list-dense" id="userList">
              </ul>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%--<div class="mdui-dialog" id="connect">--%>
  <%--<div class="mdui-dialog-title">进入失败</div>--%>
  <%--<div class="mdui-dialog-actions">--%>
    <%--<button class="mdui-btn mdui-ripple exit">退出</button>--%>
    <%--<button class="mdui-btn mdui-ripple try">重试</button>--%>
  <%--</div>--%>
<%--</div>--%>
<script>
    const socketUrl = "ws://" + window.location.host + baseUrl + "/chat/chatOn?Group_Key=${group.key}";
    const ownUserName = "${user.username}";
    const groupKey = "${group.key}";
</script>
</body>
</html>
