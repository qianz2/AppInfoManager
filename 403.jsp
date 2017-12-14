<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>服务器错误 </title>

    <!-- Bootstrap -->
    <link href="../statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../statics/css/nprogress/nprogress.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../statics/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
          <div class="col-middle">
            <div class="text-center text-center">
              <h1 class="error-number">403</h1>
              <h3>拒绝访问</h3>
              <h3>对不起,您没有权限访问！请返回到 <a href="${pageContext.request.contextPath }/gol/index.html">系统入口</a>
              </h3>
              <%-- <div class="mid_center">
                <h3>Search</h3>
                <form action="${pageContext.request.contextPath }/dev/index.html">
                  <div class="col-xs-12 form-group pull-right top_search">
                    <div class="input-group">
                      <input type="text" class="form-control" value="进入主页面!"/>
                      <span class="input-group-btn">
                              <button class="btn btn-default" type="submit">Go!</button>
                          </span>
                    </div>
                  </div>
                </form>
              </div> --%>
            </div>
          </div>
        </div>
        <!-- /page content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>
  </body>
</html>