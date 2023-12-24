package product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;

import connection.ConnectionPool;
import library.Utilities;
import objects.ProductObject;
import product.ProductService;
import product.ProductServiceImpl;

public class ProductModel {
	private ProductService p;

	public ProductModel(ConnectionPool cp) {
		this.p = new ProductServiceImpl(cp);
	}

	protected void finalize() throws Throwable {
		this.p = null;
	}

	public ConnectionPool getCP() {
		return this.p.getCP();
	}

	public void releaseConnection() {
		this.p.releaseConnection();
	}

	// ***********************Chuyen huong dieu khien tu Product
	// Impl*****************************************
	public boolean addProduct(ProductObject item) {
		return this.p.addProduct(item);
	}

	public boolean editProduct(ProductObject item, PRODUCT_EDIT_TYPE type) {
		return this.p.editProduct(item, type);
	}

	public boolean delProduct(ProductObject item) {
		return this.p.delProduct(item);
	}

	// ****************************************************************

	public ProductObject getProductObject(int id) {
		// Gan gia tri khoi tao cho doi tuong ProductObject
		ProductObject item = null;

		// Lay ban ghi
		ResultSet rs = this.p.getProduct(id);

		// Chuyen doi ban ghi thanh doi tuong
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(Utilities.decode(rs.getString("product_name")));
					item.setProduct_status(rs.getByte("product_status"));
					item.setProduct_deleted(rs.getBoolean("product_deleted"));
					item.setProduct_import_price(rs.getInt("product_import_price"));
					item.setProduct_sell_price(rs.getInt("product_sell_price"));
					item.setProduct_images(rs.getString("product_images"));
					item.setProduct_guarantee_id(rs.getShort("product_guarantee_id"));
					item.setProduct_notes(rs.getString("product_notes"));
					item.setProduct_last_modified(rs.getString("product_last_modified"));
					item.setProduct_desc(rs.getString("product_desc"));
					item.setMinInventory(rs.getShort("product_min_inven"));
					item.setMaxInventory(rs.getShort("product_max_inven"));
					item.setStoped_sell(rs.getBoolean("product_stoped_cell"));
					item.setUser_modified_id(rs.getInt("product_user_modified_id"));
					item.setProduct_bar_code(rs.getString("product_bar_code"));
					item.setPc_id(rs.getInt("pc_id"));
					item.setPc_name(rs.getString("pc_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}

	/**
	 * Phương thức lấy về danh sách đối tượng và tổng số bản ghi
	 * @param similar
	 * @param page
	 * @param pPerPage
	 * @param type
	 * @param isExport
	 * @return
	 * 		danh sách đối tượng<br/>
	 * 		Tổng số bản ghi
	 */
	public Pair<ArrayList<ProductObject>, Integer> getProductObjects(ProductObject similar, short page, byte pPerPage,
			PRODUCT_SORT_TYPE type, boolean isExport) {

		// Gan gia tri khoi tao cho doi tuong ProductObject
		ArrayList<ProductObject> items = new ArrayList<>();
		ProductObject item = null;

		// Lay ban ghi
		int at = (page - 1) * pPerPage;
		ArrayList<ResultSet> res = this.p.getProducts(similar, at, pPerPage, type);

		ResultSet rs = res.get(0);

		// Chuyen doi ban ghi thanh doi tuong
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(Utilities.decode(rs.getString("product_name")));
					item.setProduct_status(rs.getByte("product_status"));
					item.setProduct_deleted(rs.getBoolean("product_deleted"));
					item.setProduct_import_price(rs.getInt("product_import_price"));
					item.setProduct_sell_price(rs.getInt("product_sell_price"));
					item.setProduct_images(rs.getString("product_images"));
					item.setProduct_guarantee_id(rs.getShort("product_guarantee_id"));
					item.setProduct_notes(rs.getString("product_notes"));
					item.setProduct_last_modified(rs.getString("product_last_modified"));
					item.setProduct_desc(rs.getString("product_desc"));
					item.setMinInventory(rs.getShort("product_min_inven"));
					item.setMaxInventory(rs.getShort("product_max_inven"));
					item.setStoped_sell(rs.getBoolean("product_stoped_cell"));
					item.setUser_modified_id(rs.getInt("product_user_modified_id"));
					item.setProduct_bar_code(rs.getString("product_bar_code"));
					item.setPc_id(rs.getInt("pc_id"));
					item.setPc_name(rs.getString("pc_name"));

					// Dua doi tuong vao tap hop
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Lay tong so ban ghi
		int totalGlobal = 0;
		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					totalGlobal = rs.getInt("total");
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items, totalGlobal);
	}
}
