<%@page import="java.util.*"%>
<%@page import="product.*"%>
<%@page import="objects.ProductObject"%>
<%@page import="org.javatuples.*"%>
<%@page import="connection.ConnectionPool"%>
<%@page import="objects.UserObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String status = (String)request.getParameter("status");
	String type = (String)request.getParameter("type");
%>

<jsp:include page="../components/header2.jsp"></jsp:include>
<jsp:include page="../components/alert.jsp">
	<jsp:param value="<%=status %>" name="status"/>
	<jsp:param value="<%=type %>" name="type"/>
</jsp:include>

<%
	response.setCharacterEncoding("utf-8");
	
	/* UserObject currentUser = (UserObject) request.getServletContext().getAttribute("userLogined");
	
	if (currentUser == null) {
		response.sendRedirect("/home/user/user-login.jsp");
	} */
	short p = library.Utilities.getShortParam(request, "page");
	
		if (p <= 0) {
			p = 1;
		}
	
	UserObject similar = new UserObject();
	
	ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
	
	ProductControl pc = new ProductControl(cp);
	
	if (cp == null) {
		getServletContext().setAttribute("CPool", pc.getCP());
	}
	
	Quartet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE> infors = new Quartet<>(null, (short) p, (byte) 5,
			PRODUCT_SORT_TYPE.NAME);
	
	ArrayList<String> pList = pc.viewProductsList(infors);
	
	// lấy dữ liệu
	/* ObjectMapper mapper = new ObjectMapper();
	String pList = request.getAttribute("plist");
	JsonNode root = mapper.readTree(pList);
	int total = root.get("total").asInt();
	ArrayList<ProductObject> list = mapper.treeToValue(root.get("products"), new TypeReference<ArrayList<ProductObject>>()); */
	
	pc.releaseConnection();//Tra ve ket noi
%>

<div class="content-page">
	<div class="container-fluid">
		<%
			// edit modal
			out.append(pList.get(2));
			// delete modal
			out.append(pList.get(3));
			// modal ngừng kinh doanh
			out.append(pList.get(4));
		%>
		<div class="row">
			<div class="col-sm-12">
				<div class="card">
					<div class="card-header d-flex justify-content-between">
						<div class="header-title">
							<h4 class="card-title">Danh sách sản phẩm</h4>
						</div>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<div class="row justify-content-between">
								<div class="col-sm-6 col-md-6">
									<div id="user_list_datatable_info" class="dataTables_filter">
										<form class="mr-3 position-relative">
											<div class="form-group mb-0">
												<input type="search" class="form-control"
													id="exampleInputSearch" placeholder="Search"
													aria-controls="user-list-table">
											</div>
										</form>
									</div>
								</div>
								<div class="col-sm-6 col-md-6">
									<div class="user-list-files d-flex justify-content-end align-items-center">
										<div class="action d-flex" hidden>
											<div>
												Đã chọn&nbsp;<span class="count mr-1 text-primary">0</span>
												<i class="fa-solid fa-x ml-1 unchkAllBtn" style="font-size: 12px;transform: translateY(-6px); cursor: pointer;"></i>
											</div>
											<div class="dropdown">
											  <button class="btn btn-primary ml-3 rounded-sm dropdown-toggle mr-3" id="action-btn" data-toggle="dropdown" aria-expanded="false">
												Thao tác </button> 
											  <div class="dropdown-menu">
											    <a class="dropdown-item" href="#">Thêm vào kho</a>
											    <a class="dropdown-item" href="#">Another action</a>
											    <a class="dropdown-item" href="#">Something else here</a>
											  </div>
											</div>
										</div>
										<button class="btn btn-primary btn-md" type="button" role="button" id="export">
											Xuất Excel </button> 
										<button class="btn btn-primary btn-md ml-2 mr-1" type="button" id="import"> Nhập Excel </button>

										<form method="post" action="/home/product/export" hidden="hidden">
											<button type="submit" id="export-button"></button>
										</form>

										<button type="button" id="import-button"
											class="btn btn-primary mt-2" data-toggle="modal"
											data-target="#gridSystemModal" hidden="hidden">
											Launch demo modal</button>
										<div id="gridSystemModal" class="modal fade" tabindex="-1"
											role="dialog" aria-labelledby="gridModalLabel"
											aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered"
												role="document">
												<div class="modal-content">
													<form method="post" action="/home/product/import"
														enctype="multipart/form-data">
														<div class="modal-header">
															<h5 class="modal-title" id="gridModalLabel">Nhập
																file Excel</h5>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">×</span>
															</button>
														</div>
														<div class="modal-body">
															<div class="container-fluid">
																<div class="row">
																	<div class="col-lg-12">
																		<p>Custom file:</p>
																		<div class="custom-file mb-3">
																			<input class="custom-file-input" type="file"
																				name="import-excel" id="import-excel" accept=".xlsx" />
																			<label class="custom-file-label" for="import-excel">Choose
																				file</label>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary"
																data-dismiss="modal">Close</button>
															<button type="submit" class="btn btn-primary">Save
																changes</button>
														</div>
													</form>
												</div>
											</div>

										</div>
										<!--  Modal -->

									</div>
								</div>
							</div>
							<!-- Data table-->
							<table id="user-list-table"
								class="table mt-4 table-hover table-responsive" role="grid"
								aria-describedby="user-list-page-info">
								<thead class="table-primary">
									<tr >
										<th scope="col"><input type="checkbox" class="checkAll"/></th>
										<th class="" scope="col">Tên sản phẩm</th>
										<th class="" scope="col">Thể loại</th>
										<th class="" scope="col">Giá</th>
										<th class="" scope="col">Trạng thái</th>
									</tr>
								</thead>
								<tbody>
									<%
										out.append(pList.get(0));
									%>
								</tbody>
							</table>
						</div>
						<!---Phân trang--->
						<%
							out.append(pList.get(1));
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../components/footer.jsp"></jsp:include>