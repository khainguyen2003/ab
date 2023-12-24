package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import connection.ConnectionPool;
import employee.*;
import objects.*;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Dinh nghia kieu noi dung xuat ve trinh khach
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Thuong duoc dung de cung cap mot giao dien(GUI) (Cau truc HTML)
	 * Duoc goi trong 2 truong hop:<br>
	 * - thong qua URL/ URI <br>
	 * - thong qua su kien cua Form (method = "get"), 
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @param request - luu tru cac yeu cau xu ly, cac du lieu duoc gui len boi client
	 * @param response - lưu trữ các đáp ứng dữ liệu được trả về cho client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Xac dinh kieu noi dung xuat ve trinh khach
		response.setContentType(CONTENT_TYPE);
		
		//Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();
		out.append("<!doctype html>");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\">");
		out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.append("<title>Login</title>");
		out.append("<link href=\"/home/css/backend.css\" rel=\"stylesheet\" >");
		out.append("<link href=\"/home/css/bootstrap-plugin.min.css\" rel=\"stylesheet\" >");
		out.append("<link href=\"/home/css/style.css\" rel=\"stylesheet\" >");
		
		out.append("</head>");
		out.append("<body>");
		
		//Tìm tham số báo lỗi nếu có 
		String error = request.getParameter("err");
		if (error!=null) {
			out.append("<div class=\"col-lg-6 offset-lg-3 mb-5 text-bg-light\">");
			out.append("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">");
			out.append("<i class=\"fa-solid fa-triangle-exclamation\"></i>");
			switch (error) {
				case "param":
					out.append("Tham số lấy giá trị không chính xác!");
					break;
				case "value":
					out.append("Không tồn tại giá trị cho tài khoản!");
					break;
				case "notOk":
					out.append("Đăng nhập không thành công!");
					break;
				case "notLogin":
					out.append("Vui lòng đăng nhập");
					break;
				default:
					out.append("Có lỗi trong quá trình đăng nhập!");				
			}
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("</div>");
		} else {
			
		}
		
		out.append("<div id=\"loading\">");
		out.append("<div id=\"loading-center\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"wrapper\">");
		out.append("<section class=\"login-content\">");
		out.append("<div class=\"container\">");
		out.append("<div class=\"row align-items-center justify-content-center height-self-center\">");
		out.append("<div class=\"col-lg-8\">");
		out.append("<div class=\"card auth-card\">");
		out.append("<div class=\"card-body p-0\">");
		out.append("<div class=\"d-flex align-items-center auth-content\">");
		out.append("<div class=\"col-lg-6 bg-primary content-left\">");
		out.append("<div class=\"p-3\">");
		out.append("<h2 class=\"mb-2 text-white\">Sign In</h2>");
		out.append("<p>Login to stay connected.</p>");
		out.append("<form method=\"post\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"floating-label form-group\">");
		out.append("<input class=\"floating-input form-control\" type=\"email\" name=\"txtname\" placeholder=\" \">");
		out.append("<label>Email</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"floating-label form-group\">");
		out.append("<input class=\"floating-input form-control\" type=\"password\" name=\"txtpass\" placeholder=\" \">");
		out.append("<label>Password</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6\">");
		out.append("<div class=\"custom-control custom-checkbox mb-3\">");
		out.append("<input type=\"checkbox\" class=\"custom-control-input\" id=\"customCheck1\">");
		out.append("<label class=\"custom-control-label control-label-1 text-white\" for=\"customCheck1\">Remember Me</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6\">");
		out.append("<a href=\"auth-recoverpw.html\" class=\"text-white float-right\">Forgot Password?</a>");
		out.append("</div>");
		out.append("</div>");
		out.append("<button type=\"submit\" class=\"btn btn-white\">Sign In</button>");
		out.append("<p class=\"mt-3\">");
		out.append("Create an Account <a href=\"auth-sign-up.html\" class=\"text-white text-underline\">Sign Up</a>");
		out.append("</p>");
		out.append("</form>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6 content-right\">");
		out.append("<img src=\"/home/images/login/01.png\" class=\"img-fluid image-right\" alt=\"\">");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</section>");
		out.append("</div>");
		
		out.append("<script language=\"javascript\" src=\"/home/js/loginv3.js\"></script>");
		out.append("<script language=\"javascript\" src=\"/home/js/backend-bundle.min.js\"></script>");
		out.append("<script language=\"javascript\" src=\"/home/js/main.js\"></script>");
		out.append("<script language=\"javascript\" src=\"/home/js/app.js\"></script>");
		out.append("<script language=\"javascript\" src=\"/home/js/material.js\"></script>");
		out.append("</body>");
		out.append("</html>");
		
//		
//		out.print("Nghiên cứu at: <br>");
//		out.close();
//		out.print("Không dùng append");
		
	}

	/**
	 * Thường được dùng để xử lý dữ liệu do doGet gửi cho<br>
	 * Được gửi trong sự kiện của form (method = "post")
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//Lay thong tin tai khoan
		
		String username = request.getParameter("txtname");
		String userpass = request.getParameter("txtpass");
		
		if (username !=null && userpass !=null) {
			username = username.trim();
			userpass = userpass.trim();
			
			if (!username.isBlank() && !userpass.isBlank()) {
				
				//Ngữ cảnh ứng dụng: là 1 không gian bộ nhớ tồn tại ở server, chứa các đối tượng để phục vụ hoạt động của web 
				//Tham chiếu Ngữ cảnh ứng dụng: định vị không gian
				ServletContext application = getServletConfig().getServletContext();
				
				//Tìm bộ quản lí kết nối trong không gian ngữ cảnh
				ConnectionPool cp = (ConnectionPool)application.getAttribute("CPool");
				
				//Tạo đối tượng thực thi chức năng
				EmployeeControl uc = new EmployeeControl(cp);
				
				if (cp==null) {
					//
					application.setAttribute("CPool", uc.getCP());
				}
				
				//Thực hiện đăng nhập
				EmployeeObject user = uc.getEmployeeObject(username, userpass);
				
				//Trả về kêt nối
				uc.releaseConnection();
				
				if (user!=null) {
					//Tham chiếu phiên làm việc (session): là một kết nối giữa máy tính client và server. Chiếm dụng bộ nhớ bên client ,Đại diện cho 1 người
					//Khởi tạo phiên làm việc mới
					HttpSession session = request.getSession(true);
					
					
					//Đưa thông tin đăng nhập vào phiên
					session.setAttribute("userLogined", user);
					
					//Trở về giao diện chính
					response.sendRedirect("/home/index.jsp");
					
				} else {
					response.sendRedirect("/home/user/login?err=notOk");
				}
				
			} else {
				response.sendRedirect("/home/user/login?err=value");
			}
		} else {			
			//sendRedirect: vi tri tra ve 
			response.sendRedirect("/home/user/login?err=param");
		}
	}

}
