package product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.Session;

import org.apache.derby.impl.tools.ij.util;
import org.apache.tomcat.jni.Library;

import connection.ConnectionPool;
import objects.ProductObject;
import storage.StorageService;

/**
 * Servlet implementation class ProductEdit
 */
@WebServlet("/product/edit")
@MultipartConfig(
		fileSizeThreshold   = 1024 * 2,
        maxFileSize         = 1024 * 1024 * 200,
        maxRequestSize      = 1024 * 1024 * 215
)
public class ProductEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private StorageService storeService;
    private final String UPLOAD_DIR = "images/product"; 
    private final String seperator = library.Utilities_const.SEPERATOR.label;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductEdit() {
        super();
        storeService = new StorageService();
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
		request.setCharacterEncoding("utf-8");//Thiết lập tập kí tự
		
		String name = request.getParameter("txtProductName");
		short idForPost = library.Utilities.getShortParam(request, "idForPost");
		if(name != null && !name.isBlank() && idForPost != -1) {
			String barcode = request.getParameter("txtProductCode");
			short pcid = library.Utilities.getShortParam(request, "slcProductCat");
			int importPrice = library.Utilities.getIntParam(request, "txtProductImportPrice");
			int sellPrice = library.Utilities.getIntParam(request, "txtProductSellPrice");
			short minInventory = library.Utilities.getShortParam(request, "minInven");
			short maxInventory = library.Utilities.getShortParam(request, "maxInven");
			String desc = request.getParameter("txtProductDesc");
			String note = request.getParameter("txtProductNote");
			String oldPath = request.getParameter("from-db");
			
			// chức năng lưu file
			// gets absolute path of the web application
	        String applicationPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String uploadFolderPath = applicationPath + UPLOAD_DIR;
	         
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(uploadFolderPath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
	        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
	        String partname = "";
	        String updatePath = "";
	        StringBuilder imagePath = new StringBuilder();
			for(Part part : request.getParts()) {
				System.out.println(part.getName());
				String partName = part.getName();
				if(partName.startsWith("uploadImg")) {
					String fileName = part.getSubmittedFileName();
					if(fileName != null && !fileName.isBlank()) {
						Date date = new Date();
						long time = date.getTime();
						String fileType = fileName.substring(fileName.lastIndexOf("."));
						String newName = fileName.substring(0, fileName.lastIndexOf(".")) + time + fileType;
						String uploadFilePath = Path.of(uploadFolderPath + File.separator + newName).toString();
						System.out.println(uploadFilePath);
						boolean success = storeService.uploadFileToSystem(part, uploadFilePath);
						if(success) {
							imagePath.append("/home/"+ UPLOAD_DIR + "/" + newName + seperator);
						}
					}
				} else if(partName.startsWith("oldImg")) {
					// xóa ảnh đã xóa trên giao diện
					updatePath += request.getParameter(partName) + seperator;
				}
			}
			
			storeService.delFiles(oldPath, updatePath, applicationPath);
			
			System.out.println(updatePath + seperator+ imagePath.toString());
			ProductObject editProduct = new ProductObject();
			editProduct.setProduct_id(idForPost);
			editProduct.setProduct_name(name);
			editProduct.setProduct_bar_code(barcode);
			editProduct.setPc_id(pcid);
			editProduct.setProduct_import_price(importPrice);
			editProduct.setProduct_sell_price(sellPrice);
			editProduct.setMinInventory(minInventory);
			editProduct.setMaxInventory(maxInventory);
			editProduct.setProduct_images(updatePath + seperator+ imagePath.toString());
			editProduct.setProduct_desc(library.Utilities.encode(desc));
			editProduct.setProduct_notes(library.Utilities.encode(note));
			
			// Lưu thay đổi vào csdl
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			ProductControl pc = new ProductControl(cp);
			if(cp == null) {
				cp = pc.getCP();
				getServletContext().setAttribute("CPool", cp);
			}
			boolean editSuccess = pc.editProduct(editProduct, PRODUCT_EDIT_TYPE.GENERAL);
			if(editSuccess) {
				response.sendRedirect("/home/product/product-list.jsp?status=succ&type=edit");
				
			} else {
				response.sendRedirect("/home/product/product-list.jsp?status=err&type=edit");
			}
		}
	}

}
