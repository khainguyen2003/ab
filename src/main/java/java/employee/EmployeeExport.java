package employee;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import connection.ConnectionPool;
import library.Utilities;
import objects.EmployeeObject;
import objects.UserObject;
import user.USER_SORT_TYPE;
import user.UserControl;

/**
 * Servlet implementation class EmployeeExport
 */
@WebServlet("/eExport")
public class EmployeeExport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeExport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		
		//Lấy tham số URI
		String eExport = request.getParameter("export");
		
		if (eExport!=null) {
			out.append("<div id=\"eExport\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridModalLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">");
			out.append("<div class=\"modal-content\">");
			out.append("<form method=\"post\" action=\"/home/eExport\" enctype=\"multipart/form-data\">");
			out.append("<div class=\"modal-header\">");
			out.append("<h5 class=\"modal-title\" id=\"gridModalLabel\">Xuất file Excel</h5>");
			out.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>");
			out.append("</div><!--  Modal header -->");
			out.append("<div class=\"modal-body\">");
			out.append("<div class=\"container-fluid\">");
			out.append("<div class=\"row\">");
			out.append("<div class=\"col-lg-12\">");
			out.append("<p>Custom file:</p>");
			out.append("<div class=\"custom-file mb-3\">");
			out.append("<input class=\"custom-file-input\" type=\"file\" name=\"import-excel\" id=\"import-excel\" accept=\".xlsx\" />");
			out.append("<label class=\"custom-file-label\" for=\"import-excel\">Choose file</label>");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("</div><!--  Modal body -->");
			out.append("<div class=\"modal-footer\">");
			out.append("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>");
			out.append("<button type=\"submit\" class=\"btn btn-primary\">Save changes</button>");
			out.append("</div><!--  Modal footer -->");
			out.append("</form>");
			out.append("</div><!--  Modal content -->");
			out.append("</div><!--  Modal dialog -->");
			out.append("</div> <!--  Modal -->");		
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long start = System.nanoTime();

		//Lấy dữ liệu xác nhận exportfile
		String ekey = request.getParameter("ekey");
		String saveKey = (ekey!=null && !ekey.equalsIgnoreCase("")) ? ekey.trim(): "";
		
		EmployeeObject similar = new EmployeeObject();
		
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		
		EmployeeControl ec = new EmployeeControl(cp);
		
		if (cp==null) {
			getServletContext().setAttribute("CPool", ec.getCP());			
		}
		
		Quartet<EmployeeObject, Short, Byte, EMPLOYEE_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 40, EMPLOYEE_SORT_TYPE.CREATED);
		
		Pair<ArrayList<EmployeeObject>,Integer> datas = ec.getEmployeeObjects(infors);

		ec.releaseConnection();
		
		response.setContentType("utf-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employee_data.xlsx");		             
        
        OutputStream os = response.getOutputStream();
        
        //Các thuộc tính của biến filter sẽ xác định các cột được xuất
        EmployeeObject filter = new EmployeeObject();
   		
   		boolean result = library.Utilities_file.writeEmployeeExcelFile(os, datas.getValue0(), similar); 		
   
        os.close();
        
        if (!result) {
        	response.sendRedirect("/home/employee/employee-list.jsp/err=efailed");
        }
        
   		long end = System.nanoTime();
    	System.out.println(end-start);
	}

}
