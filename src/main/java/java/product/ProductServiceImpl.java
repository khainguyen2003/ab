package product;

import java.sql.*;
import java.util.ArrayList;
import connection.*;

import basic.BasicImpl;
import objects.ProductObject;

public class ProductServiceImpl extends BasicImpl implements ProductService {

	public ProductServiceImpl(ConnectionPool cp) {
		super(cp, "Product");
	}

	@Override
	public boolean addProduct(ProductObject item) {
		if (this.isExisting(item)) {
			return false;
		}

		String sql = "INSERT INTO tblProduct(";
		sql += "product_name, product_category_id, product_status, product_deleted, ";
		sql += "product_import_price, product_images, product_guarantee_id, product_notes, product_last_modified, product_sell_price, ";
		sql += "product_desc) ";
		sql	+= "VALUE(?,?,?,?,?,?,?,?,?);";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getProduct_name());
			pre.setShort(2, item.getProduct_category_id());
			pre.setShort(3, item.getProduct_status());
			pre.setBoolean(4, item.isProduct_deleted());
			pre.setInt(5, item.getProduct_import_price());
			pre.setString(6, item.getProduct_images());
			pre.setInt(7, item.getProduct_guarantee_id());
			pre.setString(8, item.getProduct_notes());
			pre.setString(9, item.getProduct_last_modified());
			pre.setInt(10, item.getProduct_sell_price());
			pre.setString(11, item.getProduct_desc());

			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	// Phương thức ràng buộc sự duy nhất của Product_name
	private boolean isExisting(ProductObject item) {
		// Trường hợp giả định tài khoản chưa tồn tại
		boolean flag = false;

		String sql = "SELECT product_id FROM tblProduct WHERE product_name='" + item.getProduct_name() +"'; ";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public boolean editProduct(ProductObject item, PRODUCT_EDIT_TYPE type) {
		String sql = "UPDATE tblProduct SET ";
		switch (type) {
			case GENERAL: 
				sql += "product_name=?, product_pc_id=?, product_status=?, ";
				sql += "product_images=?, product_guarantee_id=?, product_notes=?, product_last_modified=?, ";
				sql += "product_import_price=?, product_sell_price=?, product_min_inven=?, product_max_inven=?, product_desc=?, product_bar_code=? ";
				sql += "WHERE product_id=?; ";
				break;
			case ISSELL: 
				sql += "product_stoped_cell=? ";
				sql += "WHERE product_id=?; ";
				break;
			
			case DELETE: 
				sql += "product_deleted=? ";
				sql += "WHERE product_id=?; ";
				break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			switch (type) {
				case GENERAL: 
					pre.setString(1, item.getProduct_name());
					pre.setShort(2, item.getProduct_pc_id());
					pre.setShort(3, item.getProduct_status());
					pre.setString(4, item.getProduct_images());
					pre.setInt(5, item.getProduct_guarantee_id());
					pre.setString(6, item.getProduct_notes());
					pre.setString(7, item.getProduct_last_modified());
					pre.setInt(8, item.getProduct_import_price());
					pre.setInt(9, item.getProduct_sell_price());
					pre.setInt(10, item.getMinInventory());
					pre.setInt(11, item.getMaxInventory());
					pre.setString(12, item.getProduct_desc());
					pre.setString(13, item.getProduct_bar_code());
					
					pre.setInt(14, item.getProduct_id());
					break;
				case ISSELL: 
					System.out.println(item.isStoped_sell());
					pre.setBoolean(1, item.isStoped_sell());
					pre.setInt(2, item.getProduct_id());
					break;
				case DELETE: 
					pre.setBoolean(1, item.isProduct_deleted());
					pre.setInt(2, item.getProduct_id());
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);

			}
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delProduct(ProductObject item) {
		// TODO Auto-generated method stub
		if (!this.isEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tblProduct WHERE product_id=? ";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt((short)1, item.getProduct_id());

			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	private boolean isEmpty(ProductObject item) {
		boolean flag = true;
		
		StringBuilder sql = new StringBuilder();
		// sql.append("SELECT article_id FROM tblarticle WHERE article_author_name=" + item.getProduct_name() + "; ");
		sql.append("SELECT product_id FROM tblproduct WHERE product_manager_id=" + item.getProduct_id() + "; ");
		sql.append("SELECT Product_id FROM tblProduct WHERE Product_parent_id=" + item.getProduct_id() + ";");
		
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		
		for(ResultSet rs : res) {
			try {
				if(rs != null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public ResultSet getProduct(int id) {
		String sql = "SELECT p.*, pc.pc_id, pc.pc_name FROM tblProduct p LEFT JOIN `tblpc` pc";
		sql += "WHERE (product_id=?) AND (product_deleted=0)";

		return this.get(sql, id);
	}
	
	// Phương thức này có thể thực hiện nhều câu lệnh cùng lúc
	// similar là đối tượng tương tự
	@Override
	public ArrayList<ResultSet> getProducts(ProductObject similar, int at, byte total, PRODUCT_SORT_TYPE type) {

		String sql = "SELECT p.*, pc.pc_id, pc.pc_name FROM tblProduct p LEFT JOIN `tblpc` pc ";
		sql+= "ON p.product_pc_id = pc.pc_id ";
		String sql2 = "SELECT COUNT(product_id) AS total FROM tblProduct ";
		sql2 += this.createConditions(similar) + "; ";
		sql += this.createConditions(similar);
		switch (type) {
			case NAME:
				sql += "ORDER BY p.product_name ASC ";
				break;
			case PRICE:
				sql += "ORDER BY product_address ASC ";
				break;
			case MODIFIED:
				sql += "ORDER BY product_last_modified DESC ";
				break;
			default:
				sql += "ORDER BY product_id ASC ";
		}
		if(total > 0) {
			sql += "LIMIT " + at + ", " + total + "; ";
		} else {
			sql += ";";
		}
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		multiSelect.append(sql2);
		
		System.out.println(multiSelect);
		
		return this.getReList(multiSelect.toString());
	}
	
	private String createConditions(ProductObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar !=  null) {
			
			// Từ khóa tìm kiếm
			String key = similar.getProduct_name();
			if(key != null && !key.equalsIgnoreCase("")) {
				conds.append(" AND ");
				conds.append("(product_name LIKE '%"+key+"%')");
			}
			
			if(similar.isProduct_deleted()) {
				conds.append(" AND (product_deleted=1) ");
			} else {
				conds.append(" AND (product_deleted=0) ");
			}
		}
		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		return conds.toString();
	}
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		ProductService p = new ProductServiceImpl(cp);
		ProductObject po = new ProductObject();
		po.setProduct_name("MacBook Air 13 inch M1 2020 7-core GPU");
		po.setProduct_category_id((short)0);
		po.setProduct_status((byte)0);
		po.setProduct_deleted(false);
		po.setProduct_import_price(18990000);
		po.setProduct_images("D:\\learning\\ki 5\\1soCNPTPM\\BTL-1SOCNPTPM\\code\\src\\main\\webapp\\images\\product\\macbook-air-m1-2020-gray-600x600.jpg");
		po.setProduct_notes(null);
		po.setProduct_last_modified("29/10/2023");
		p.addProduct(po);
	}
}
