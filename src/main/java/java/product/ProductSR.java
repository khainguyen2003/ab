package product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionPool;
import library.Utilities_date;
import objects.EmployeeObject;
import objects.ProductObject;

/**
 * Servlet implementation class ProductSR
 */
@WebServlet("/product/sr")
public class ProductSR extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Kiểm tra đăng nhập
		EmployeeObject user = (EmployeeObject)request.getSession().getAttribute("userLogined");
		short id = library.Utilities.getShortParam(request, "id");
		String url = "/home/product/product-list.jsp";
//		System.out.println("permis: "+ user.getUser_permission());
		if(user != null && id>0) {
			// Nếu user là admin hoặc user là cha của người dùng muốn xóa
//			if(user.getUser_permission() >= 4) {
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			ProductControl pc = new ProductControl(cp);
			ProductObject sProduct = new ProductObject();
			sProduct.setProduct_id(id);
			sProduct.setProduct_last_modified(Utilities_date.getDate());
			
			// đang ở trong danh sách đã dừng kinh doanh hay ko. Nếu ở trong thì là null
			String isStopedCell = request.getParameter("s");
			String isRestore = request.getParameter("r");
			if(isRestore != null) {
				sProduct.setStoped_sell(false);
			} else {
				sProduct.setStoped_sell(true);
			}
			
			boolean result;
			String status;
			if(isStopedCell == null) {
				result = pc.delProduct(sProduct);
				// Chuyển đến danh sách sản phẩm đã ngừng kinh doanh
				url += "?sell";
			} else {
				result = pc.editProduct(sProduct, PRODUCT_EDIT_TYPE.ISSELL);
			}
			pc.releaseConnection();
			if(result) {
//					status = "succ";
				response.sendRedirect(url);
			} else {
//					status = "err";
				response.sendRedirect(url + "&err=notok");
			}
//			} else {
//				response.sendRedirect("/home/product/product-list.jsp?err=nopermis");
//			}
		} else {
			response.sendRedirect("/home/product/product-list.jsp?err=sell");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
