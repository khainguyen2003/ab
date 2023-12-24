<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>Webkit | Responsive Bootstrap 4 Admin Dashboard Template</title>
      
      <!-- Favicon -->
      <link rel="shortcut icon" href="/home/images/favicon.ico" />
      <link rel="stylesheet" href="/home/css/backend-plugin.min.css">
      <link rel="stylesheet" href="/home/css/backend3.css">
      <link rel="stylesheet" href="/home/vendor/line-awesome/dist/line-awesome/css/line-awesome.min.css">
      <link rel="stylesheet" href="/home/vendor/bootstrap-icons/bootstrap-icons.min.css">
   	  <link rel="stylesheet" href="/home/vendor/fontawesome/css/all.min.css">
	  <link rel="stylesheet" href="/home/css/style.css">
	  <link rel="stylesheet" href="/home/vendor/bootstrap/css/bootstrap.min.css" />
	  <script src="/home/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	  
	  <link rel="stylesheet" href="/home/vendor/bootstrap/css/bootstrap-utilities.min.css" />
	  <link rel="stylesheet" href="/home/vendor/remixicon/fonts/remixicon.css">
	  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
  <body>
    <!-- loader Start -->
    <div id="loading">
          <div id="loading-center">
          </div>
    </div>
    <!-- loader END -->
    <!-- Wrapper Start -->
    <div class="wrapper">
      <!-- ============ sidebar start =========== -->
        <jsp:include page="./sidebar2.jsp" flush="true"></jsp:include>
      <!-- ============ sidebar end =========== -->
        
        <!-- ============ top-navbar start =========== -->
        <jsp:include page="./top-navbar.jsp" flush="true"></jsp:include>
       <!-- ============ top-navbar end =========== -->