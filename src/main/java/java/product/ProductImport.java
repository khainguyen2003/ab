package product;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import connection.ConnectionPool;
import library.Utilities_file;
import objects.ProductObject;
import objects.UserObject;
import user.UserControl;

/**
 * Servlet implementation class ProductImport
 */
@WebServlet("/product/import")
@MultipartConfig
public class ProductImport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductImport() {
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
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		
		ProductControl pc = new ProductControl(cp);
		
		if (cp==null) {
			getServletContext().setAttribute("CPool", pc.getCP());		
		}
		
	    // Get the uploaded Excel file from the request
        Part filePart = request.getPart("import-excel");
        
        // Initialize an InputStream to read the uploaded file
        InputStream fileContent = filePart.getInputStream();
        String fileName = filePart.getName();
        BufferedInputStream buffer = new BufferedInputStream(fileContent);
		
		ArrayList<Object> result = Utilities_file.readExcelFile(buffer,"product");
		
		result.forEach(object->{
			ProductObject product = (ProductObject) object;
			pc.addProduct(product);
		}); 
		
		fileContent.close();
		pc.releaseConnection();	 
		response.sendRedirect("/home/product");  
	}

}
