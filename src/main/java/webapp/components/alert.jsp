<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="library.Utilities"%>
<%
	String mess = "";
	String status = (String)request.getParameter("status");
	String icon = "";
	if(status != null) {
		String type = (String)request.getParameter("type");
		switch(status) {
			case "err":
				icon = "<i class=\"fa-solid fa-circle-xmark text-danger me-2\" ></i>";
				switch(type) {
					case "edit":
						mess = Utilities.encode("Cập nhật không thành công");
						break;
					case "upload":
						mess = Utilities.encode("Tải lên không thành công");
						break;
					default:
						mess = Utilities.encode("Có lỗi xin vui lòng kiểm tra lại");
				}
				break;
			case "succ":
				icon = "<i class=\"fa-solid fa-circle-check text-success me-2\"></i>";
				switch(type) {
					case "edit":
						mess = Utilities.encode("Cập nhật thành công");
						break;
					default:
						mess = Utilities.encode("Có lỗi xin vui lòng kiểm tra lại");
				}
		}
%>
	<div class="toast position-fixed top-0 start-50 translate-middle-x bg-white" role="alert" aria-live="assertive" aria-atomic="true" id="message" style="
	    z-index: 9999;">
	<div class="d-flex">
	  <div class="toast-body">
	    <p><%=icon %><% out.append(Utilities.decode(mess)); %></p>
	  </div>
	  <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
	</div>
	</div>
	<script>
		const toastLiveExample = document.getElementById('message');
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample, {
			delay: 3000
		})
		toastBootstrap.show();
		
	</script>
<%}%>