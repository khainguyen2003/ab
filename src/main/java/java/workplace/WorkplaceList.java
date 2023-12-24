package workplace;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Sextet;

import connection.ConnectionPool;
import library.Utilities;
import objects.*;

/**
 * Servlet implementation class WorkplaceList
 */
@WebServlet("/WorkplaceList")
public class WorkplaceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Dinh nghia kieu noi dung xuat ve trinh khach
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkplaceList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		
		if (user!=null) {
			view(request, response, user);
		} else {
			response.sendRedirect("/home/login");
		}
	}
	
	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		short wpPage = (short) Utilities.getShortParam(request, "page");

		if (wpPage<0){
			wpPage = 1;
		}
		
		byte wpPerPage = (byte) Utilities.getByteParam(request, "wpperpage");

		if (wpPerPage<=0){
			wpPerPage = 5;
		}
		
		byte isOpenModal = (byte) Utilities.getByteParam(request, "Modal");
		
		int id = Utilities.getIntParam(request, "id");
		System.out.println("Id:"+id);
		System.out.println("page:"+wpPage);
	/* 	EmployeeObject similar = new EmployeeObject(); */
		WorkplaceObject similar = new WorkplaceObject();
		if (id>0) {
			similar.setWorkplace_id(id);
		}
		
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		
		WorkplaceControl wpc = new WorkplaceControl(cp);
		
		if (cp==null) {
			getServletContext().setAttribute("CPool", wpc.getCP());			
		}
		
		Sextet<EmployeeObject, 
		WorkplaceObject, 
		Short, 
		Byte ,
		WORKPLACE_SORT_TYPE, Boolean> infors = new Sextet<>(null, similar ,(short) wpPage, (byte) wpPerPage, WORKPLACE_SORT_TYPE.NAME, false);
		

		ArrayList<String> view = wpc.viewWorkplacesList(infors, isOpenModal , request.getRequestURI());
		
		wpc.releaseConnection();//Tra ve ket noi
		
		// Xac dinh kieu noi dung xuat ve trinh khach
		response.setContentType(CONTENT_TYPE);
		
		StringBuilder out = new StringBuilder();
		
		out.append("<div class=\"row justify-content-between\">");
		out.append("<div class=\"col-sm-6 col-md-6\">");
		out.append("<div id=\"user_list_datatable_info\" class=\"dataTables_filter\">");
		out.append("<form class=\"mr-3 position-relative\">");
		out.append("<div class=\"form-group mb-0\">");
		out.append("<input type=\"search\" class=\"form-control\" id=\"exampleInputSearch\" placeholder=\"Search\" aria-controls=\"user-list-table\">");
		out.append("</div>");
		out.append("</form>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-sm-6 col-md-6\">");
		out.append("<div class=\"user-list-files d-flex\"> ");
		out.append(view.get(2));
		out.append("<a class=\"bg-primary\" class=\"bg-primary\" id=\"import\">");
		out.append("Nhập Excel");
		out.append("</a>");
		out.append("");
		out.append("<a class=\"bg-primary\" class=\"bg-primary\" id=\"export\" >");
		out.append("Xuất Excel");
		out.append("</a>	");
		out.append("");
		out.append("<button type=\"button\" id=\"export-button\" class=\"btn btn-primary mt-2\" data-toggle=\"modal\" data-target=\"#eExport\" hidden=\"hidden\">\";");
		out.append("Launch demo modal\"");
		out.append("</button>");
		out.append("");
		out.append("<div id=\"eExport\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridModalLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-xl modal-dialog-centered\" role=\"document\">");
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h5 class=\"modal-title\" id=\"gridModalLabel\">Xuất file Excel</h5>");
		out.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">																	");
		out.append("<div class=\"container-fluid\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">														   			                   ");
		out.append("<birt:viewer id=\"birtViewer\" reportDesign=\"report.rptdesign\"");
		out.append("pattern=\"run\"");
		out.append("height=\"450\"");
		out.append("width=\"1000\"");
		out.append("format=\"html\">");
		out.append("</birt:viewer>     ");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>									");
		out.append("</div>");
		out.append("<form method=\"post\" action=\"/home/eExport\" enctype=\"multipart/form-data\">");
		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Save changes</button>");
		out.append("</div>");
		out.append("</form>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div><!-- End Export Modal -->  ");
		out.append("</div>");
		out.append("</div>");
		out.append("</div><!-- row justify-content-between -->	");
		
		out.append(view.get(0));
		out.append(view.get(1));
		//Tạo đối tượng thực hiện xuất nội dung
		
        request.setAttribute("currentPageData", out.toString());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/workplace/workplace-list.jsp");
        
        if (dispatcher!=null) {
        	dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("/error?err=404NotFound");
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserObject currentUser = (UserObject) request.getSession().getAttribute("userLogined");
		
		request.setCharacterEncoding("utf-8");//Thiết lập tập kí tự
		
		String workplaceName = request.getParameter("workplaceName");
		byte workplaceType = library.Utilities.getByteParam(request, "workplaceType");
		int workplaceManager = library.Utilities.getIntParam(request, "workplaceManager");
		String workplaceAddress = request.getParameter("workplaceAddress");
		String workplacePhoneNumber = request.getParameter("workplacePhoneNumber");
		String workplaceEmail = request.getParameter("workplaceEmail");
		String workplaceNotes = request.getParameter("workplaceNotes");
	
		
		if (workplaceName!=null && !workplaceName.equalsIgnoreCase("")
			&& 	workplaceManager>0 && workplaceType>=0
			
			) {
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			
			WorkplaceControl wpc = new WorkplaceControl(cp);
			
			if (cp==null) {
				request.setAttribute("CPool", wpc.getCP());
			}
			
			WorkplaceObject similar = new WorkplaceObject();
			
			similar.setWorkplace_name(workplaceName);
			similar.setWorkplace_type(workplaceType);
			similar.setWorkplace_manager_id(workplaceManager);
			similar.setWorkplace_address(workplaceAddress);
			similar.setWorkplace_phone(workplacePhoneNumber);
			similar.setWorkplace_email(workplaceEmail);
			similar.setWorkplace_notes(workplaceNotes);
			similar.setWorkplace_manager_id(currentUser.getUser_id());
			similar.setWorkplace_created_date(library.Utilities_date.getCurrentDate());
			
			
			ArrayList<WorkplaceObject> similarList = new ArrayList<WorkplaceObject>();
			similarList.add(similar);
			ArrayList<WorkplaceStorageDetail> storage = new ArrayList<WorkplaceStorageDetail>();
			
			boolean result = wpc.addWorkplace(similarList, storage);
			
			wpc.releaseConnection();
			
			if (result) {
				response.sendRedirect("/home/WorkplaceList");					
			} else {
				response.sendRedirect("/home/WorkplaceList?err=add");	
			}
		} else {
			response.sendRedirect("/home/WorkplaceList?err=invalid");	
		}
		
		
	}

}
