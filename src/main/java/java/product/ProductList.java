package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quartet;

import connection.*;
import library.Utilities;
import objects.ProductObject;
import objects.UserObject;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/product/list")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if(user != null) {
			view(request, response, user);
		} else {
			response.sendRedirect("/home/login?err=notlogin");
		}
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();
		
		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		// Tạo đối tượng thực thi chức năng
		ProductControl pc = new ProductControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", pc.getCP());
		}
		
		// Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key != null && !key.equalsIgnoreCase("")) ? key.trim() : "";
		
		// Lấy câu trúc
		ProductObject similar = new ProductObject();
		similar.setProduct_import_price(user.getUser_id());
		similar.setProduct_status(user.getUser_permission());
		similar.setProduct_name(saveKey);
		
		// Tìm tham số xác định loại danh sách
		String trash = request.getParameter("trash");
		String title, pos;
		if(trash == null) {
			similar.setProduct_deleted(false);
			pos = "plist";
			title = "Danh sách sản phẩm";
		} else {
			similar.setProduct_deleted(true);
			pos = "ptrash";
			title = "Danh sách sản phẩm bị xóa";
		}

		short page = Utilities.getShortParam(request, "page");
		if(page < 1) {
			page = 1;
		}
		// Lấy cấu trúc
		Quartet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE> infors = new Quartet<>(similar, page, (byte) 10,
				PRODUCT_SORT_TYPE.NAME);

		String productList = pc.getProductLists(infors);

		// Trả về kết nối
		pc.releaseConnection();
		
		request.setAttribute("plist", productList);
		request.getRequestDispatcher("/home/product/home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
