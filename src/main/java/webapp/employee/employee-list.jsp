<%@page import="library.Utilities_text"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../components/header.jsp" flush="true"></jsp:include>

<%@ page import="connection.*, objects.*" %>
<%@ page import="java.util.*, org.javatuples.*" %>
<%@ page import="employee.*" %>
<%@ page import="library.*" %>
<%@ page import="org.eclipse.birt.report.engine.api.*" %>

<script type="text/javascript" src="birt-viewer/preview?__show=report1.js"></script>
<script type="text/javascript" src="birt-viewer/preview?__show=frameset.js"></script>
 <%@ taglib uri="/birt.tld" prefix="birt" %>


<%
	response.setCharacterEncoding("utf-8");
	
	UserObject currentUser = (UserObject) request.getServletContext().getAttribute("userLogined");
	
	if (currentUser!=null){
		response.sendRedirect("/home/user/user-login.jsp");
	}

	short uPage = (short) Utilities.getShortParam(request, "page");

	if (uPage<0){
		uPage = 1;
	}
	
	EmployeeObject similar = new EmployeeObject();
	
	EmployeeControl ec = new EmployeeControl(null);
	
	Quartet<EmployeeObject, Short, Byte, EMPLOYEE_SORT_TYPE> infors = new Quartet<>(null, (short) uPage, (byte) 3, EMPLOYEE_SORT_TYPE.NAME);
	
	ArrayList<String> view = ec.viewEmployeesList(infors);
	
	ec.releaseConnection();//Tra ve ket noi
	
%>



<div class="content-page">
<div class="container-fluid">
   <div class="row">
      <div class="col-sm-12">
         <div class="card">
            <div class="card-header d-flex justify-content-between">
               <div class="header-title">
                  <h4 class="card-title">Danh sách nhân viên</h4>
               </div>
            </div>
            <div class="card-body">
               <div class="table-responsive">
                  <div class="row justify-content-between">
                     <div class="col-sm-6 col-md-6">
                        <div id="user_list_datatable_info" class="dataTables_filter">
                           <form class="mr-3 position-relative">
                              <div class="form-group mb-0">
                                 <input type="search" class="form-control" id="exampleInputSearch" placeholder="Search"
                                    aria-controls="user-list-table">
                              </div>
                           </form>
                        </div>
                     </div>
                     <div class="col-sm-6 col-md-6">
                        <div class="user-list-files d-flex">
                            <a class="bg-primary" class="bg-primary" id="export" >
								Xuất Excel
                          	</a>	
                          	
                          	<a class="bg-primary" class="bg-primary" id="import">
								Nhập Excel
                          	</a>	
                          	
                          	<!-- Export Modal -->
                          	<button type="button" id="export-button" class="btn btn-primary mt-2" data-toggle="modal" data-target="#eExport" hidden="hidden">";
								Launch demo modal"
							</button>
							
							<div id="eExport" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
									<div class="modal-content">
								
								   	  	
											  <div class="modal-header">
												 <h5 class="modal-title" id="gridModalLabel">Xuất file Excel</h5>
												 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
											  </div>
											  <div class="modal-body">																	
													<div class="container-fluid">
	         											<div class="row">
														    <div class="col-lg-12">														   			                   
										                        <birt:viewer id="birtViewer" reportDesign="report.rptdesign"
													                pattern="run"
													                height="450"
													                width="1000"
													                format="html">
													            </birt:viewer>     
												            </div>
										            	</div>
										            </div>									
											  </div>
											  <form method="post" action="/home/eExport" enctype="multipart/form-data">
											  <div class="modal-footer">
												 <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
												 <button type="submit" class="btn btn-primary">Save changes</button>
											  </div>
									  		 </form>
							   		</div>
								</div>
	                       	</div>
	                       	<!-- End Export Modal -->            							

							<!-- Import Modal -->
							 <button type="button" id="import-button" class="btn btn-primary mt-2" data-toggle="modal" data-target="#eImport" hidden="hidden">
									Launch demo modal
							 </button>
							 <div id="eImport" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
								   <div class="modal-content">
								   	  	<form method="post" action="/home/EmployeeImport" enctype="multipart/form-data">
											  <div class="modal-header">
												 <h5 class="modal-title" id="gridModalLabel">Nhập file Excel</h5>
												 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
											  </div>
											  <div class="modal-body">																	
													<div class="container-fluid">
	         											<div class="row">
														    <div class="col-lg-12">        			                   
										                        <p>Custom file:</p>
										                        <div class="custom-file mb-3">
									                         		<input class="custom-file-input" type="file" name="import-excel" id="import-excel" accept=".xlsx" />									                     
										                        	<label class="custom-file-label" for="import-excel">Choose file</label>
										                        </div>               
												            </div>
										            	</div>
										            </div>									
											  </div>
											  <div class="modal-footer">
												 <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
												 <button type="submit" class="btn btn-primary">Save changes</button>
											  </div>
									  	  </form>
								   	  </div>
									</div>
	                       		</div>
	                       		<!-- End Import Modal -->
                     </div>
                  </div>
                  <table id="user-list-table" class="table table-striped dataTable mt-4" role="grid"
                     aria-describedby="user-list-page-info">
                     <thead>
                        <tr class="ligth">
                           <th>Profile</th>
                           <th>Tên</th>
                           <th>Liên hệ</th>
                           <th>Email</th>
                           <th>Trạng thái</th>
                           <th>Company</th>
                           <th>Join Date</th>
                           <th style="min-width: 100px">Action</th>
                        </tr>
                     </thead>
                     <tbody>
                       	<% 
                       		out.append(view.get(0));
                       	%>
                     </tbody>
                  </table>
               </div>
				<!---Phân trang--->
				<%
					out.append(view.get(1));
				%>	
            </div>
         </div>
      </div>
   </div>
</div>
      </div>
    </div>

<jsp:include page="../components/footer.jsp" flush="true"></jsp:include>