package workplace;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Sextet;

import connection.ConnectionPool;
import library.Utilities;
import objects.EmployeeObject;
import objects.UserObject;
import objects.WorkplaceObject;

/**
 * Servlet implementation class WorkplaceProfile
 */
@WebServlet("/WorkplaceProfile")
public class WorkplaceProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkplaceProfile() {
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

		if (wpPerPage<0){
			wpPerPage = 5;
		}
		
	/* 	EmployeeObject similar = new EmployeeObject(); */
		WorkplaceObject similar = new WorkplaceObject();
		
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
		
		ArrayList<String> view = wpc.viewWorkplacesList(infors, request.getRequestURI());
		
		wpc.releaseConnection();//Tra ve ket noi
		
		// Xac dinh kieu noi dung xuat ve trinh khach
		response.setContentType(CONTENT_TYPE);
		
		StringBuilder out = new StringBuilder();
		
		out.append(view.get(0));
		out.append(view.get(1));
		
		//Tạo đối tượng thực hiện xuất nội dung
		
        request.setAttribute("currentPageData", out.toString());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WorkplaceList");
        
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
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WorkplaceProfile");
	        
        if (dispatcher!=null) {
        	dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("/error?err=404NotFound");
        }   
	}

}
