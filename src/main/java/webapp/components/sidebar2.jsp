<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ============ sidebar start =========== -->
<div class="iq-sidebar sidebar-default">
    <div class="iq-sidebar-logo d-flex align-items-center justify-content-around">
        <a class="nav-link" href="/home/index.jsp" class="header-logo">
            <img src="/home/images/logo.svg" alt="logo">
            <h3 class="logo-title light-logo">Webkit</h3>
        </a>
        <div class="iq-menu-bt-sidebar ml-0 d-flex align-items-center">
            <i class="las la-bars wrapper-menu"></i>
        </div>
    </div>
    <div class="data-scrollbar" data-scroll="1">
        <nav class="iq-sidebar-menu">
            <ul id="iq-sidebar-toggle" class="iq-menu">
                <li class="navbar-item">
                    <a href="/home/index.jsp" class="svg-icon nav-link">                        
                        <svg class="svg-icon" width="25" height="25" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                            <polyline points="9 22 9 12 15 12 15 22"></polyline>
                        </svg>
                        <span class="ml-2">Tổng quan</span>
                    </a>
                </li>
                <li class="navbar-item">
                    <a href="#goods" class="collapsed svg-icon nav-link" data-toggle="collapse" aria-expanded="false">                        
                        <i class="las la-box nav-item-icons"></i>
                        <span class="ml-2">Hàng hóa</span>
	                    <i class="las la-angle-right iq-arrow-right arrow-active"></i>
	                    <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                    </a>
                    <ul id="goods" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/product/product-list.jsp">
                                <i class="las la-table nav-item-icons"></i><span class="ml-2">Danh mục</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-tags nav-item-icons"></i><span class="ml-2">Thiết lập giá</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-clipboard-check nav-item-icons"></i><span class="ml-2">Kiểm kho</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="navbar-item">
                    <a class="nav-link" href="/home/workplace/workplace-list.jsp" class="svg-icon">                        
                        <i class="las la-table nav-item-icons"></i><span class="ml-2">Vị trí/phòng</span>
                    </a>
                </li>
                <li class="navbar-item">
                    <a class="nav-link" href="#transaction" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">                        
                        <i class="las la-exchange-alt nav-item-icons"></i>
                        <span class="ml-2">Giao dịch</span>
                        <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                        <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                    </a>
                    <ul id="transaction" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-file-invoice-dollar nav-item-icons"></i><span class="ml-2">Hóa đơn</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-reply nav-item-icons"></i><span class="ml-2">Trả hàng</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-dolly-flatbed nav-item-icons"></i><span class="ml-2">Nhập kho</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="navbar-item">
                    <a class="nav-link" href="#partner" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">                        
                        <i class="las la-handshake nav-item-icons"></i>
                        <span class="ml-2">Đối tác</span>
                        <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                        <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                    </a>
                    <ul id="partner" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                        <li class="navbar-item">
	                        <a class="nav-link" href="/home/user/user-list.jsp">
                                <i class="las la-user nav-item-icons"></i><span class="ml-2">Khách hàng</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-reply nav-item-icons"></i><span class="ml-2">Nhà cung cấp</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-dolly-flatbed nav-item-icons"></i><span class="ml-2">Thẻ dịch vụ</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="navbar-item">
                    <a class="nav-link" href="#employee" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">                        
                        <i class="las la-handshake nav-item-icons"></i>
                        <span class="ml-2">Nhân viên</span>
                        <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                        <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                    </a>
                    <ul id="employee" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                    	<li class="navbar-item">
		                    <a class="nav-link" href="#employee-detail" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">                        
		                        <i class="las la-user nav-item-icons"></i>
		                        <span class="mx-1"> Thông tin</span>
		                        <i class="las la-angle-right iq-arrow-right arrow-active"></i>
		                        <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
		                    </a>
		                    <ul id="employee-detail" class="iq-submenu collapse" data-parent="#employee-detail">
	                            <li class="navbar-item">
		                            <a class="nav-link" href="/home/employee/employee-list.jsp">
		                                <i class="las la-user nav-item-icons"></i><span class="ml-2">Danh sách</span>
		                            </a>
		                        </li>
				                <li class="navbar-item">
		                            <a class="nav-link" href="/home/app/user-add.html">
		                                <i class="las la-table nav-item-icons"></i><span class="ml-2">Chấm công</span>
		                            </a>
		                        </li>
		                        <li class="navbar-item">
		                            <a class="nav-link" href="/home/app/user-add.html">
		                                <i class="las la-table nav-item-icons"></i><span class="ml-2">Tính lương</span>
		                            </a>
		                        </li>
                            </ul>                         
                        </li>
                     
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-table nav-item-icons"></i><span class="ml-2">Bảng tính lương</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-table nav-item-icons"></i><span class="ml-2">Bảng hoa hồng</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="navbar-item">
                    <a class="nav-link" href="/home/backend/index.html" class="svg-icon">                        
                        <i class="las la-hand-holding-usd nav-item-icons"></i>
                        <span class="ml-2">Sổ quỹ</span>
                    </a>
                </li>
                <li class="navbar-item">
                    <a class="nav-link" href="#report" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">                        
                        <i class="las la-handshake nav-item-icons"></i>
                        <span class="ml-2">Báo cáo</span>
                        <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                        <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                    </a>
                    <ul id="report" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-user nav-item-icons"></i><span class="ml-2">Cuối ngày</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-reply nav-item-icons"></i><span class="ml-2">Bán hàng</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-dolly-flatbed nav-item-icons"></i><span class="ml-2">Tài chính</span>
                            </a>
                        </li>
                        <li class="navbar-item">
                            <a class="nav-link" href="/home/app/user-add.html">
                                <i class="las la-dolly-flatbed nav-item-icons"></i><span class="ml-2">Bảng hoa hồng</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div class="pt-5 pb-2"></div>
    </div>
</div>
<!-- ============ sidebar end =========== -->
