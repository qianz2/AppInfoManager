<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开发者登录</title>

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
            <form action="${pageContext.request.contextPath }/gol/dologin.html" method="post">
              <h1>登录到开发者后台</h1>
              <span style="color: red">${error }</span>
              <div>
                <input type="text" class="form-control" name="devCode" value="test001" placeholder="Username" required="" />
              </div>
              <div>
                <input type="password" class="form-control" name="devPassword" value="123456" placeholder="Password" required="" />
              </div>
              <div>
              	<input type="submit" name="login" value="登录"/>
                <!-- <a class="btn btn-default submit" href="developer/main.jsp">登录</a> -->
                <a class="reset_pass" href="#">忘记密码?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">没有账户?
                  <a href="#signup" class="to_register"> 注册账户 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> T171!</h1>
                  <p>©2017 T171版权所有</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>创建账户</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <a class="btn btn-default submit" href="index.html">保存</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">已经有账户 ?
                  <a href="#signin" class="to_register"> 登录 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                 <div>
                  <h1><i class="fa fa-paw"></i> T168!</h1>
                  <p>©2017 T168版权所有</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>