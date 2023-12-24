<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="connection.*, objects.*" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>Webkit | Responsive Bootstrap 4 Admin Dashboard Template</title>
      
      <!-- Favicon -->
      <link rel="shortcut icon" href="/home/images/favicon.ico" />
      <link rel="stylesheet" href="/home/css/backend-plugin.min.css">
      <link rel="stylesheet" href="/home/css/backend.css?v=1.0.0">
      <link rel="stylesheet" href="/home/vendor/line-awesome/dist/line-awesome/css/line-awesome.min.css">
      <link rel="stylesheet" href="/home/vendor/remixicon/fonts/remixicon.css">
	  <link rel="stylesheet" href="/home/css/apexcharts.css">
	  <link rel="stylesheet" href="/home/css/style.css">
	  <link rel="stylesheet" href="/home/css/all.min.css">
 	  <link rel="stylesheet" href="/home/vendor/datatables/cdn.datatables.net_select_1.7.0_css_select.dataTables.min.css">
    
    	<script src="/home/js/apexcharts.js"></script>
    	<script src="/home/vendor/datatables/code.jquery.com_jquery-3.7.0.js"></script>
    	<script src="/home/vendor/datatables/select/cdn.datatables.net_1.13.7_js_jquery.dataTables.min.js"></script>
    	
    </head>
  <body class="  ">
    <!-- loader Start -->
    <div id="loading">
          <div id="loading-center">
          </div>
    </div>
    <!-- loader END -->
    <!-- Wrapper Start -->
    <div class="wrapper">
      <!-- ============ sidebar start =========== -->
        <jsp:include page="./sidebar.jsp" flush="true"></jsp:include>
      <!-- ============ sidebar end =========== -->
        
        <!-- ============ top-navbar start =========== -->
        <jsp:include page="./top-navbar.jsp" flush="true"></jsp:include>
       <!-- ============ top-navbar end =========== -->