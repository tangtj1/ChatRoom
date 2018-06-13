<%--
  Created by IntelliJ IDEA.
  User: tang
  Date: 2018/5/23
  Time: 23:26
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/taglib.jsp" %>
<html>
<head>
  <%@ include file="include/head.jsp" %>
  <title>Title</title>
</head>
<body class="login-page">
<div class="wrapper">
  <div class="login-box">
    <div class="login-logo">
      Chat Room
    </div>
    <div class="login-box-body">
      <form action="${basePath}/login" method="post">
        <div class="form-group has-feedback">
          <input type="text" name="username" maxlength="20" class="form-control" placeholder="用户名"/>
          <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <input type="password" name="password" maxlength="20" class="form-control" placeholder="密码"/>
          <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
        </div>
        <div class="form-group text-right">
          <input type="submit" class="btn btn-primary btn-block" value="登录"/>
        </div>
        <div class="col-md-12 text-right">
          <a href="${basePath}/signup" class="">注册帐号</a>
        </div>
      </form>
      <div class="login-box-msg">
        <c:if test="${error_msg != null}">
          <div class="alert alert-danger col-sm-12">
            <p>${error_msg}</p>
          </div>
        </c:if>
      </div>
    </div>
  </div>
</div>
</body>
</html>
