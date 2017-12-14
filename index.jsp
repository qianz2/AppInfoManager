<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APP后台管理平台</title>

   <!-- Bootstrap -->
    <link href="../statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../statics/css/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="../statics/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../statics/css/custom.min.css" rel="stylesheet">
</head>
  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
              <h1>选择登录方式</h1>
              <div>
                <a class="btn btn-default" href="${pageContext.request.contextPath }/gol/login.html">开发者登录</a>
                <a class="btn btn-default" href="${pageContext.request.contextPath }/gol/Login">后台管理登录</a>
              </div>

            

              <div class="separator">
                <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> 刘诗鹏!</h1>
                  <p>©2017 T171版权所有</p>
                </div>
              </div>
          </section>
        </div>

      </div>
    </div>
  </body>
</html>