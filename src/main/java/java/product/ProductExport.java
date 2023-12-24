package product;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Pair;
import org.javatuples.Quintet;

import connection.ConnectionPool;
import library.Utilities_file;
import objects.ProductObject;
import objects.UserObject;
import user.USER_SORT_TYPE;
import user.UserControl;

/**
 * Servlet implementation class ProductExport
 */
@WebServlet("/product/export")
public class ProductExport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductExport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy dữ liệu xác nhận exportfile
		String ekey = request.getParameter("ekey");
		String saveKey = (ekey != null && !ekey.equalsIgnoreCase("")) ? ekey.trim() : "";

		ProductObject similar = new ProductObject();

		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		ProductControl pc = new ProductControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", pc.getCP());
		}

		Quintet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE, Boolean> infors = new Quintet<>(null, (short) 1, (byte) 40,
				PRODUCT_SORT_TYPE.MODIFIED, true);

		Pair<ArrayList<ProductObject>, Integer> datas = pc.getProducts(infors);

		pc.releaseConnection();

		response.setContentType("utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=product_data.xlsx");

		OutputStream os = response.getOutputStream();

		boolean result = Utilities_file.writeProductExcel(os, datas.getValue0());

		os.close();

		if (!result) {
			response.sendRedirect("/home/product/?err=efailed");
		}
	}

}
