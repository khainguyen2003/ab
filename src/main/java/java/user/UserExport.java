package user;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import org.javatuples.*;


import connection.ConnectionPool;
import objects.UserObject;
import library.*;
/**
 * Servlet implementation class UserExport
 */
@WebServlet("/UserExport")
public class UserExport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserExport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		long start = System.nanoTime();
		
		//Lấy dữ liệu xác nhận exportfile
		String ekey = request.getParameter("ekey");
		String saveKey = (ekey!=null && !ekey.equalsIgnoreCase("")) ? ekey.trim(): "";
		
		UserObject similar = new UserObject();
		
		
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		
		UserControl uc = new UserControl(cp);
		
		if (cp==null) {
			getServletContext().setAttribute("CPool", uc.getCP());			
		}
		
		Quintet<UserObject, Short, Byte, USER_SORT_TYPE, Boolean> infors = new Quintet<>(null, (short) 1, (byte) 40, USER_SORT_TYPE.CREATED, true);
		
		Pair<ArrayList<UserObject>,Integer> datas = uc.getUserObjects(infors);

		uc.releaseConnection();
		
		response.setContentType("utf-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=user_data.xlsx");
		       
        OutputStream os = response.getOutputStream();
        
        boolean result = Utilities_file.writeUserExcelFile(os, datas.getValue0());
        
        os.close();
        
        if (!result) {
        	response.sendRedirect("/home/user/user-list.jsp/err=efailed");
        }
    	long end = System.nanoTime();
    	System.out.println("User:");
    	System.out.print(end-start);
	}

}
