<%@ page import="library.Utilities_text"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../components/header2.jsp" flush="true"></jsp:include>

<%@ page import="connection.*, objects.*" %>
<%@ page import="java.util.*, org.javatuples.*" %>
<%@ page import="workplace.*" %>
<%@ page import="library.*" %>


<%  
	response.setCharacterEncoding("utf-8");
	
	UserObject currentUser = (UserObject) request.getServletContext().getAttribute("userLogined");
	
	if (currentUser!=null){
		response.sendRedirect("/home/user/user-login.jsp");
	}

	short wpPage = (short) Utilities.getShortParam(request, "page");

	if (wpPage<0){
		wpPage = 1;
	}
	
	byte wpPerPage = (byte) Utilities.getByteParam(request, "wpperpage");

	if (wpPerPage<0){
		wpPerPage = 11;
	}
	
/* 	EmployeeObject similar = new EmployeeObject(); */
	WorkplaceObject similar = new WorkplaceObject();
	
	ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
	
	WorkplaceControl uc = new WorkplaceControl(cp);
	
	if (cp==null) {
		getServletContext().setAttribute("CPool", uc.getCP());			
	}
	
	
	Sextet<EmployeeObject, 
	WorkplaceObject, 
	Short, 
	Byte,
	WORKPLACE_SORT_TYPE, Boolean> infors = new Sextet<>(null, similar ,(short) wpPage, (byte) wpPerPage, WORKPLACE_SORT_TYPE.NAME, false);
	
	ArrayList<String> view = uc.viewWorkplacesList(infors);

	
	uc.releaseConnection();//Tra ve ket noi
%>
       
<div class="content-page">
<div class="container-fluid">
   <div class="row">
<% 
	if(wpPerPage>9){
%>
      <div class="col-sm-12">
         <div class="card">
            <div class="card-header d-flex justify-content-between">
               <div class="header-title">
                  <h4 class="card-title">Danh sách nơi làm việc</h4>
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
                          
                            <form method="post" action="/home/UserExport" hidden="hidden">
                            	<button type="submit" id="export-button">
								</button>		
							</form>	
							
							 <button type="button" id="import-button" class="btn btn-primary mt-2" data-toggle="modal" data-target="#gridSystemModal" hidden="hidden">
									Launch demo modal
							 </button>
							 <div id="gridSystemModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
								   <div class="modal-content">
								   	  <form method="post" action="/home/UserImport" enctype="multipart/form-data">
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
								
							 </div> <!--  Modal -->
									
                        </div>
                     </div>
                  </div>
                  <!-- Data table-->
                  <table id="user-list-table" class="table table-striped dataTable mt-4" role="grid"
                     aria-describedby="user-list-page-info">
                     <thead>
                        <tr class="ligth">
                           <th class="col-1">Profile</th>
                           <th class="col-2">Tên</th>
                           <th class="col-1">Liên hệ</th>
                           <th class="col-2">Email</th>
                           <th class="col-3">Địa chỉ</th>
 <!--                           <th class="col-1">Trạng thái</th> -->
                           <th class="col-2" style="min-width: 100px">Action</th>
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
<% } else { %>
<div class="col-sm-12">
	<div class="card">
		<div class="card-header d-flex justify-content-between">
	       <div class="header-title">
	          <h4 class="card-title">Danh sách nơi làm việc</h4>
	       </div>
	    </div>
		<div class="card-body">
		    <div class="row justify-content-between mb-3">
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
		              
		                <form method="post" action="/home/UserExport" hidden="hidden">
		                	<button type="submit" id="export-button"></button>		
						</form>	
				
						 <button type="button" id="import-button" class="btn btn-primary mt-2" data-toggle="modal" data-target="#gridSystemModal" hidden="hidden">
								Launch demo modal
						 </button>
						 <div id="gridSystemModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
							   <div class="modal-content">
							   	  <form method="post" action="/home/UserImport" enctype="multipart/form-data">
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
							
						 </div> <!--  Modal -->
						
			           </div>
			        </div>
		    	</div>
				<% 
				out.append(view.get(0)); 
				out.append(view.get(1));
				%> 
		</div>   
	</div>
</div>
<% } %>    
 
   </div> <!-- row -->
</div> <!-- container-fluid -->
</div> <!-- contentPage -->
    </div>

<jsp:include page="../components/footer.jsp" flush="true"></jsp:include>