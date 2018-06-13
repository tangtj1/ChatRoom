<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/taglib.jsp" %>
<html>
<head>
  <%@ include file="include/head.jsp" %>
  <link href="${basePath}/static/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet"/>
  <script src="${basePath}/static/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
  <title>用户注册</title>
</head>
<body class="login-page">
<div class="wrapper">
  <div class="login-box">
    <div class="login-logo">
      Chat Room
    </div>
    <div class="login-box-body">
      <form action="${basePath}/signup" method="post" id="signupForm">
        <div class="form-group has-feedback">
          <input type="text" name="username" maxlength="20" class="form-control" placeholder="用户名"/>
          <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <input type="password" name="password" maxlength="20" class="form-control" placeholder="密码"/>
          <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <input type="password" name="repassword" maxlength="20" class="form-control" placeholder="确认密码"/>
          <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
        </div>
        <div class="form-group">
          <input type="submit" class="btn btn-primary btn-block" value="用户注册" id="btnSubmit"/>
        </div>
        <div class="col-md-12 text-right">
          <a href="${basePath}/" class="">用户登录</a>
        </div>
      </form>
      <div class="login-box-msg">
        <c:if test="${message != null}">
          <div class="row">
            <div class="alert alert-danger col-sm-12">
              <p>${message}</p>
            </div>
          </div>
        </c:if>
        <c:if test="${success != null}">
          <div class="row">
            <div class="alert alert-success col-sm-12">
              <p>${success}</p>
            </div>
          </div>
        </c:if>
      </div>
    </div>
  </div>
</div>
<script>
    $("#signupForm").bootstrapValidator({
        submitButtons: "#btnSubmit",
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不得为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 10,
                        message: '长度必须在5-10之间'
                    },
                    remote: {
                        message: '用户名已被使用',
                        type: 'post',
                        url: '${basePath}/signup/check',
                        delay: 500
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不得为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 10,
                        message: '长度必须在5-10之间'
                    }
                }
            },
            repassword: {
                validators: {
                    notEmpty: {
                        message: '密码不得为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 10,
                        message: '长度必须在5-10之间'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码不一致'
                    }
                }
            },
        }
    })
</script>
</body>
</html>
