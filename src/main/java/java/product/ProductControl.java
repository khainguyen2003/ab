package product;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import connection.ConnectionPool;
import objects.ProductObject;

public class ProductControl {
private ProductModel pm;
	
	public ProductControl(ConnectionPool cp) {
		this.pm = new ProductModel(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.pm.getCP();
	}
	
	public void releaseConnection() {
		this.pm.releaseConnection();
	}

	
	public boolean addProduct(ProductObject item) {
		return this.pm.addProduct(item);
	}
	
	public boolean editProduct(ProductObject item, PRODUCT_EDIT_TYPE type) {
		return this.pm.editProduct(item, type);
	}
	
	public boolean delProduct(ProductObject item) {
		return this.pm.delProduct(item);
	}
	
	/**
	 * Phương thức lấy product theo id
	 * @param id của sản phẩm cần lấy
	 * @return sản phẩm cần lấy nếu tìm được
	 * Cập nhật ngày 26/10/2023
	 */
	public ProductObject getProductObject(int id) {
		return this.pm.getProductObject(id);
	}
	

	/**
	 * Phương thức trả về tập các product theo infors truyền vào
	 * @param infors các điều kiện để lấy dữ liệu
	 * @return cặp giá trị danh sách product lấy được và tổng số bản ghi lấy được<br/>
	 * 
	 * Cập nhật ngày 26/10/2023
	 */
	public Pair<ArrayList<ProductObject>,Integer> getProducts(Quintet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE, Boolean> infors) {
		//Lay du lieu
		ProductObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		PRODUCT_SORT_TYPE pst = infors.getValue3();
		
		boolean isExport = infors.getValue4();
				
		return this.pm.getProductObjects(similar, page, total, pst, isExport);
	}
	
	/** 
	 * Phương thức trả về danh sách giao diện cho phần trình bày danh sách product
	 * @param infors các thông tin bổ sung
	 * @return danh sách giao diện trình bày cho phần product
	 * 
	 * <br/>Cập nhật ngày 26/10/2023
	 */
	public ArrayList<String> viewProductsList(Quartet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE> infors){
		//Lay du lieu
		ProductObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte uPerPage = infors.getValue2();
		PRODUCT_SORT_TYPE ust = infors.getValue3();
		
		Pair<ArrayList<ProductObject>,Integer> datas = this.pm.getProductObjects(similar, page, uPerPage, ust, false);
		
		return ProductLibrary.viewProductList(datas, infors);
	}
	
	public String getProductLists(Quartet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE> infors) {
		//Lay du lieu
		ProductObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte uPerPage = infors.getValue2();
		PRODUCT_SORT_TYPE ust = infors.getValue3();
		
		Pair<ArrayList<ProductObject>,Integer> datas = this.pm.getProductObjects(similar, page, uPerPage, ust, false);
		
		// Chuyển dữ liệu thành json
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();
		root.put("total", datas.getValue1());
		root.putPOJO("products", datas.getValue0());
		
		String res = "";
		try {
			res = mapper.writeValueAsString(root);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
}
