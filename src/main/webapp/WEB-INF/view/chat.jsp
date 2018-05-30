<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/taglib.jsp" %>
<html>
<head>
  <%@ include file="include/head.jsp" %>
  <title>Title</title>
  <script src="${basePath}/static/common/chat.js"></script>
</head>
<body>
<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-default col-md-12">
      <p class="navbar-text h3">ChatOnline</p>
      <a href="${basePath}/logout" class="btn btn-default navbar-btn navbar-right">退出登录</a>
    </div>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-md-9" id="romeCards">
      <c:forEach var="rome" items="${romeList}">
      <div class="col-md-6">
          <div class="small-box bg-green">
            <div class="inner">
              <h3>${rome.name}</h3>
              <div class="h4">在线人数 :  ${rome.users.size()} / ${rome.maxUserSize}</div>
            </div>
            <div class="icon">
              <i class="ion ion-person-add"></i>
            </div>
            <a href="${basePath}/chat/rome/${rome.key}" class="small-box-footer">
              加 入 <i class="fa fa-arrow-circle-right"></i>
            </a>
      </div>
    </div>
      <%--<div class="col-md-6 cardContainer">--%>
      <%--<a href="${pageContext.request.contextPath}/chat/rome/${rome.groupKey}">--%>
      <%--<div class="card panel panel-default">--%>
      <%--<div class="panel-heading">--%>
      <%--<div class="panel-title">${rome.groupName}</div>--%>
      <%--</div>--%>
      <%--<div class="panel-body">--%>
      <%--<div class="h3">--%>
      <%--<div class="label label-primary">在线人数--%>
      <%--<div class="badge"> ${rome.liveUserCount} / ${rome.maxSizeofUser}</div>--%>
      <%--</div>--%>
      <%--</div>--%>
      <%--</div>--%>
      <%--</div>--%>
      <%--</a>--%>
      <%--</div>--%>
    </c:forEach>
  </div>
  <div class="col-md-3">

    <div class="infoBlock">
      <a href="#"><span class="glyphicon glyphicon-user" style="font-size: 25px"
                        aria-hidden="true">UserName</span></a>
    </div>

    <div class="infoBlock">
      <div class="input-group">
        <input type="text" class="form-control" id="searchRome" placeholder="搜索房间">
        <span class="input-group-btn">
              <button class="btn btn-default" type="button" id="searchRomeBtn">搜索</button>
        </span>
      </div>
      <div class="btnClass">
        <button id="create" class="btn btn-default btn-block">创建房间</button>
      </div>
    </div>
    <div class="infoBlock">
      <ul class="list-group">
        <li class="list-group-item">
          在线人数<span class="badge">${onlineCount}</span>
        </li>
        <li class="list-group-item">
          房间数量<span class="badge">${romeList.size()}</span>
        </li>
      </ul>
    </div>
  </div>
</div>
</div>
<div class="modal fade" id="myModel" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">创建房间</h4>
      </div>
      <div class="modal-body">
        <div class="form-inline">
          <label for="groupName" class="control-label">房间名:</label>
          <input class="form-control" type="text" id="groupName" name="groupName"/>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="createGroup">创建</button>
      </div>
    </div>
  </div>
</div>
<script>
    $(function () {
        let btn = $("#test");
        btn.click(function () {
            let ws = new WebSocket("ws://" + window.location.host + "/chat/ChatOn");
            console.log(ws);
        })
    })
</script>
<style>
  div.infoBlock {
    padding: 15px 15px 15px 15px;
    margin-bottom: 15px;
    background-color: #f8f8f8;
    border: #e7e7e7 solid 1px
  }

  div.btnClass {
    padding: 5px 0 5px 0;
    margin-top: 10px
  }

  .list-group {
    margin-bottom: 0;
  }
</style>
</body>
</html>
